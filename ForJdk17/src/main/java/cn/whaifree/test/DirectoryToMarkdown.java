//package cn.whaifree.test;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @version 1.0
// * @Author whai文海
// * @Date 2024/3/26 16:52
// * @注释
// */
//public class DirectoryToMarkdown {
//    public static void main(String[] args) {
//        // 指定你要读取的文件夹路径
//        String directoryPath = "D:\\project\\LeetCode\\src";
//
//        // 生成Markdown文件的内容
//        String markdownContent = generateMarkdownContent(directoryPath);
//
//
//        System.out.println(markdownContent);
//        // 将Markdown内容写入文件
//        File markdownFile = new File("D:\\project\\LeetCode\\目录.md");
//        try (FileWriter writer = new FileWriter(markdownFile)) {
//            writer.write(markdownContent);
//            System.out.println("Markdown文件已生成: " + markdownFile.getAbsolutePath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String generateMarkdownContent(String directoryPath) {
//        File directory = new File(directoryPath);
//        if (!directory.exists() || !directory.isDirectory()) {
//            return "指定的路径不存在或不是一个文件夹";
//        }
//
//        // 获取文件夹中的所有文件和子文件夹
//        File[] files = directory.listFiles();
//        if (files == null) {
//            return "文件夹为空";
//        }
//
//        // 使用流来构建Markdown列表
//        List<String> links = Arrays.stream(files)
//                .map(file -> {
//                    String fileName = file.getName();
//                    String filePath = directoryPath + "/" + fileName;
//                    // 转换为Markdown格式的链接
//                    return "- [" + fileName + "](#" + fileName.replace(" ", "-").toLowerCase() + ")\n";
//                })
//                .collect(Collectors.toList());
//
//        // 构建完整的Markdown内容
//        StringBuilder markdownBuilder = new StringBuilder();
//        markdownBuilder.append("# 目录\n\n");
//        links.forEach(markdownBuilder::append);
//
//        return markdownBuilder.toString();
//    }
//}
