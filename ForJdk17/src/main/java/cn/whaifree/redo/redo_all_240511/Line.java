package cn.whaifree.redo.redo_all_240511;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/15 22:06
 * @注释
 */
public class Line {

    public static void main(String[] args) throws FileNotFoundException {
        List<String> s = FileUtil.readLines(new File("D:\\project\\LeetCode\\src\\main\\java\\cn\\whaifree\\redo\\redo_all_240511\\README.md"), Charset.defaultCharset());
        int line = (int) s.stream().filter(i -> !i.trim().isEmpty()).count();
        System.out.println(line);
    }
}
