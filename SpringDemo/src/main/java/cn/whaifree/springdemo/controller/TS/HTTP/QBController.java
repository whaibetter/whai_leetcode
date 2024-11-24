package cn.whaifree.springdemo.controller.TS.HTTP;

import cn.whaifree.springdemo.controller.TS.common.ProcessStrategy;
import cn.whaifree.springdemo.controller.TS.common.ProcessTarget;
import cn.whaifree.springdemo.controller.TS.common.TargetDown;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 21:21
 * @注释
 */

@RestController
public class QBController {

    @RequestMapping("/TargetDown")
    public void targetDown(String msg) {

        ProcessStrategy processStrategy = ProcessTarget.getProcessStrategy(ProcessTarget.TARGET_DOWN);
        processStrategy.process(msg);
    }


    public void compressImage(InputStream inputStream, float quality, String fileFullName) {
        try {
            BufferedImage read = ImageIO.read(inputStream);
            File out = new File(fileFullName);
            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
            OutputStream os = new java.io.FileOutputStream(out);
            JPEGImageWriteParam param = new JPEGImageWriteParam(null);
            param.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);  // 压缩质量
            writer.setOutput(ImageIO.createImageOutputStream(os));
            writer.write(null, new javax.imageio.IIOImage(read, null, null), param);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/TargetFind")
    public void targetFind(String msg) {
        ProcessStrategy processStrategy = ProcessTarget.getProcessStrategy(ProcessTarget.TARGET_FIND);
        processStrategy.process(msg);
    }
}
