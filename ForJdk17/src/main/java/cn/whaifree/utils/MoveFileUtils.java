package cn.whaifree.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/29 16:54
 * @注释
 */
public class MoveFileUtils {


    public static void main(String[] args) {
        // 源文件夹路径
        String srcFolder = "C:\\Users\\wenhai\\Desktop\\bagu";
        // 目标文件夹路径
        String destFolder = "C:\\Users\\wenhai\\Downloads\\新建文件夹";

        copyMdFiles(srcFolder, destFolder, List.of("md", "docx", "pdf"), 40);
    }

    public static void copyMdFiles(String srcFolder, String destFolder, String afterFix) {
        copyMdFiles(srcFolder, destFolder, Collections.singletonList(afterFix), 0);
    }

    public static void copyMdFiles(String srcFolder, String destFolder, String afterFix, int split) {
        copyMdFiles(srcFolder, destFolder, Collections.singletonList(afterFix), split);

    }

    public static void copyMdFiles(String srcFolder, String destFolder, List<String> afterFix, int splitSize) {
        File srcDir = new File(srcFolder);
        File destDir = new File(destFolder);

        // 确保目标文件夹存在
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

        // 初始化计数器
        AtomicInteger counter = new AtomicInteger(0);
        // 初始化子文件夹编号
        AtomicInteger subFolderNumber = new AtomicInteger(1);
        // 遍历源文件夹中的所有文件和子文件夹
        try {
            Files.walk(srcDir.toPath())
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        for (String fix : afterFix) {
                            if (path.toString().endsWith(fix)) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .forEach(path -> {
                        try {
                            // 每移动50个文件，创建一个新的子文件夹
                            if (counter.get() % splitSize == 0) {
                                String subFolderPath = Paths.get(destDir.getAbsolutePath(), "folder_" + subFolderNumber).toString();
                                File subFolder = new File(subFolderPath);
                                if (!subFolder.exists()) {
                                    subFolder.mkdirs();
                                }
                                subFolderNumber.getAndIncrement();
                            }

                            // 计算目标路径
                            String subFolderPath = Paths.get(destDir.getAbsolutePath(), "folder_" + (subFolderNumber.get() - 1)).toString();
                            Path destPath = Paths.get(subFolderPath, path.getFileName().toString());
                            Files.copy(path, destPath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Copy: " + path + " -> " + destPath);

                            // 更新计数器
                            counter.incrementAndGet();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
