package cn.whaifree.springdemo.controller.TS.common;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/22 21:22
 * @注释
 */
@Component
public class TargetImage implements ProcessStrategy {

    private final Map<Integer, TreeMap<Integer, byte[]>> imageMap = new ConcurrentHashMap<>();

    /**
     * @param frame
     */
    @Override
    public void process(byte[] frame) {
        try {

            // 读取第5-8个字节作为id
            int id = getIntByByteArray(frame, 5, 8);
            int seq = getIntByByteArray(frame, 9, 12);
            int sumGramSize = getIntByByteArray(frame, 13, 16); // 数据报数量
            if (!imageMap.containsKey(id)) {
                imageMap.put(id, new TreeMap<>());
            }
            TreeMap<Integer, byte[]> treeMap = imageMap.get(id);
            treeMap.put(seq, frame);
            if (treeMap.size() == sumGramSize) {
                // 满了
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                for (int i = 1; i <= sumGramSize; i++) {
                    byte[] fragment = treeMap.get(i);
                    if (fragment != null) {
                        // 假设图像数据从第17字节开始
                        outputStream.write(fragment, 17, fragment.length - 17);
                    }
                }
                outputStream.write(frame);
                // 输入到Minio
                // minioClient.putObject("test", "image.jpg", outputStream.toByteArray(), null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 封装成object
        process(frame);
    }

    public int getIntByByteArray(byte[] frame, int left, int right) {
        int result = 0;
        for (int i = left; i <= right; i++) {
            result = result | (frame[i] & 0xFF) << (right - i) * 8;
        }
        return result;
    }


    @Override
    public void process(Object o) {
        System.out.println("图像");

    }

}
