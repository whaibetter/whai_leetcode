package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * 28. 找出字符串中第一个匹配项的下标
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 *
 * 示例 1：
 *
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * 示例 2：
 *
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class LeetCode28 {

    @Test
    public void test() {
        String haystack = "aabaadaabaaf";
        String needle = "aabaaf";
        int i = new Solution1().strStr(haystack, needle);
        System.out.println(i);
    }

    class Solution {
        public int strStr(String haystack, String needle) {

            int begin = 0;
            int length = needle.length();
            int end = begin + length;
            while (end <= haystack.length()) {
                if (haystack.substring(begin, end).equals(needle)) {
                    return begin;
                }
                begin++;
                end++;

            }
            return -1;
        }
    }

    // KMP
    class Solution1 {
        public int strStr(String haystack, String needle) {
            int[] next = next(needle);
            System.out.println(Arrays.toString(next));
            int length = haystack.length();
            // [0, 1, 0, 1, 2, 0]
            // aabaadaabaaf
            // aabaaf
            int j = 0;
            for (int i = 0; i < length; i++) {
                // 往前跳
                while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
                    j = next[j - 1];
                }
                if (needle.charAt(j) == haystack.charAt(i)) {
                    j++;
                }
                if (j == needle.length()) {
                    return i - needle.length() + 1;
                }
            }
            return -1;
        }

        // 求Next数组
        public int[] next(String s) {
            int[] next = new int[s.length()];
            int j = 0;
            for (int i = 1; i < s.length(); i++) {
                // 不匹配
                while (j > 0 && s.charAt(i) != s.charAt(j)) {
                    j = next[j - 1];
                }
                // 匹配
                if (s.charAt(i) == s.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
            return next;
        }
    }
}


class Dfather{
    static int count = 1;
    static{
        System.out.println("Initialize class Dfather");
    }
}

class Dson extends Dfather{
    static{
        System.out.println("Initialize class Dson");
    }
}

class Test4 {
    public static void main(String[] args) {
        String a = "d"; //字面量
        String b = "d"; //字面量，存常量池
        System.out.println(a == b);
        a = new String("d"); //对象，存堆
        System.out.println("a=" + a);
        System.out.println("b=" + b);
        System.out.println(a == b);
    }
}
