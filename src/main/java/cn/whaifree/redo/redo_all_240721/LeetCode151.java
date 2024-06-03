package cn.whaifree.redo.redo_all_240721;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/21 16:48
 * @注释
 */
public class LeetCode151 {
    public static void main(String[] args) {
        String s = "   a c    ";
        System.out.println(reverseWords(s));
    }

    public static String reverseWords(String s) {
        // 去掉前后的空格
        // 去掉中间的空格
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].equals(" ") && !split[i].isEmpty()) {
                sb.append(split[i]).append(" ");
            }
        }
        String sbString = sb.toString();
        if (sbString.length() > 1 && sbString.charAt(sbString.length() - 1) == ' ') {
            sbString = sbString.substring(0, sbString.length() - 1);
        }
        return sbString;
    }

    class Solution {
        /**
         * 反转字符串s中的单词顺序。
         *
         * @param s 输入的字符串，可能包含前后空格。
         * @return 返回反转单词顺序后的字符串，不包含前后空格。
         */
        public String reverseWords(String s) {
            // 去除前后空白串
            s = s.trim();

            // 将字符串s按一个或多个空格分割成单词数组
            String[] split = s.split(" +");

            // 使用StringBuilder来高效地构建反转后的字符串
            StringBuilder stringBuilder = new StringBuilder();

            // 从数组的末尾开始向前遍历，依次添加单词到StringBuilder中，并在单词间加上空格
            for (int i = split.length - 1; i > 0; i--) {
                stringBuilder.append(split[i]).append(" ");
            }

            // 添加数组的第一个单词，即原字符串的第一个单词，到StringBuilder末尾
            stringBuilder.append(split[0]);

            // 返回构建好的反转后的字符串
            return stringBuilder.toString();
        }

    }

}
