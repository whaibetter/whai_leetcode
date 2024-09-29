package cn.whaifree.radom;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/8 11:09
 * @注释
 */

public class LeetCode28 {

    @Test
    public void test() {

        int i = new Solution().strStr("aabaadaabaaf", "aabaaf");
        System.out.println(i);

    }

    class Solution {
        public int strStr(String haystack, String needle) {
            int[] next = constructNext(needle);

            int j = 0;
            for (int i = 0; i < haystack.length(); i++) {
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                if (j == needle.length()) {
                    return i - j + 1;
                }
            }

            return -1;

        }

        // 构造next数组
        public int[] constructNext(String needle) {
            int length = needle.length();
            int[] next = new int[length];
            int j = 0;// 前缀
            for (int i = 1; i < length; i++) {
                // 如果不匹配 往前跳
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    j = next[j - 1];
                }
                // 如果匹配 设置next[i]=j
                if (needle.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
            return next;
        }
    }


}
