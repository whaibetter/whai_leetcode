package cn.whaifree.redo.redo_all_240721;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/23 23:37
 * @注释
 */
public class LeetCode28 {
    public static void main(String[] args)
    {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack, needle));
        System.out.println(strStr("a", "a"));
        System.out.println(strStr("mississippi", "issip"));
    }
    public static int strStr(String haystack, String needle)
    {
        int len = needle.length();
        for (int i = 0; i <= haystack.length() - len; i++) {
            String substring = haystack.substring(i, i + len);
            if (substring.equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
