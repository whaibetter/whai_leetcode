package cn.whaifree.springdemo.controller.minio;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/16 9:38
 * @注释
 */
@RestController
@Slf4j
public class MinioController {
    @Resource
    private ImageUploader imageUploader;
    /**
     * 外网图片转存缓存
     */
    private LoadingCache<String, String> imgReplaceCache = CacheBuilder.newBuilder().maximumSize(300).expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
        @Override
        public String load(String img) {
            try {
                InputStream stream = null;
                if (img.startsWith("http")) {
                    // 下载变输入
                    HttpRequest get = HttpUtil.createGet(img);
                    get.header("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                    HttpResponse response = get.execute();
                    stream = response.bodyStream();
                }else {
                    return "";
                }
                URI uri = URI.create(img);
                String path = uri.getPath();
                int index = path.lastIndexOf(".");
                String fileType = null;
                if (index > 0) {
                    // 从url中获取文件类型
                    fileType = path.substring(index + 1);
                }
                return imageUploader.upload(stream, fileType);
            } catch (Exception e) {
                log.error("外网图片转存异常! img:{}", img, e);
                return "";
            }
        }
    });


    @PostMapping("/changeLink")
    public String changeLink(String url) throws ExecutionException {
        return imgReplaceCache.get(url);
    }
}
@Slf4j
@ConditionalOnExpression(value = "#{'minio'.equals(environment.getProperty('image.oss.type'))}")
@Component
class MinioOssWrapper implements ImageUploader, InitializingBean {

    private MinioClient minioClient;
    @Autowired
    @Setter
    @Getter
    private ImageProperties properties;


    @Override
    public String upload(InputStream inputStream, String fileType) {
        try {
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);
            // 计算md5作为文件名，避免重复上传
            String fileName = MD5.create().digestHex(bytes);
            ByteArrayInputStream input = new ByteArrayInputStream(bytes);

            fileName = fileName + "." + fileType;
            log.info("上传文件名：{}", fileName);

            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(properties.getOss().getBucket())
                    .object(fileName)
                    .stream(input, input.available(), -1)
                    .contentType(fileType)
                    .build();

            ObjectWriteResponse response = minioClient.putObject(args);

            // 获取response状态码
            Headers headers = response.headers();
            log.info(headers.toString());


            StringBuilder sb = new StringBuilder();
            sb.append(properties.getOss().getEndpoint()).append("/").append(response.bucket()).append("/").append(response.object());

            return sb.toString();

        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }

    @Override
    public boolean uploadIgnore(String fileUrl) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 创建OSSClient实例。
        log.info("init ossClient");
        minioClient = MinioClient.builder()
                .credentials(
                        properties.getOss().getAk(),
                        properties.getOss().getSk()
                )
                .endpoint(properties.getOss().getEndpoint())
                .build();
    }
}

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "image")
class ImageProperties {
    private String absTmpPath; // 存储绝对路径
    private String webImgPath; // 存储相对路径
    private String tmpUploadPath; // 上传文件的临时存储目录
    private String cdnHost; // 访问图片的host
    private OssProperties oss;
}

@Data
class OssProperties {
    private String prefix; // 上传文件前缀路径
    private String type; // oss类型
    //下面几个是oss的配置参数
    private String endpoint;
    private String ak;
    private String sk;
    private String bucket;
    private String host;
}


interface ImageUploader {
    String DEFAULT_FILE_TYPE = "txt";
//    Set<MediaType> STATIC_IMG_TYPE = new HashSet<>(Arrays.asList(MediaType.ImagePng, MediaType.ImageJpg, MediaType.ImageWebp, MediaType.ImageGif));

    /**
     * 文件上传
     *
     * @param input
     * @param fileType
     * @return
     */
    String upload(InputStream input, String fileType);

    /**
     * 判断外网图片是否依然需要处理
     *
     * @param fileUrl
     * @return true 表示忽略，不需要转存
     */
    boolean uploadIgnore(String fileUrl);
}
