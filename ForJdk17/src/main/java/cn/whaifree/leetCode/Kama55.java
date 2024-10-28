package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。给定一个字符串 s 和一个正整数 k，请编写一个函数，将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。
 *
 * 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
 *
 * 输入：输入共包含两行，第一行为一个正整数 k，代表右旋转的位数。第二行为字符串 s，代表需要旋转的字符串。
 *
 * 输出：输出共一行，为进行了右旋转操作后的字符串。
 */
public class Kama55 {

    @Test
    public void test() {
        String s = "abcdefg";
        int k = 2;
        String result = new Solution().reverse(s, k);
        System.out.println(result);
    }

    public static void main(String[] args) {

    }

    class Solution{

        /**
         * 逆转
         * abcde 2
         * cbaed 两个区间分别逆转
         * deabc 整体逆转
         * @param s
         * @param target
         * @return
         */
        public String reverse(String s, int target) {
            StringBuilder stringBuilder = new StringBuilder(s);
            reverseString(stringBuilder, 0, target - 1);
            reverseString(stringBuilder, target, stringBuilder.length() - 1);
            reverseString(stringBuilder, 0, stringBuilder.length() - 1);
            return stringBuilder.toString();
        }

        public void reverseString(StringBuilder stringBuilder,int start, int end) {
            while (start < end) {
                char tmp = stringBuilder.charAt(start);
                stringBuilder.setCharAt(start, stringBuilder.charAt(end));
                stringBuilder.setCharAt(end, tmp);
                start++;
                end--;
            }
        }
    }
}
