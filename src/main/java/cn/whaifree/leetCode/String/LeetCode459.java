package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/7 11:49
 * @注释
 */
public class LeetCode459 {
    @Test
    public void test() {
        String s = "aa";
        Solution solution = new Solution();
        boolean repeatedSubstringPattern = solution.repeatedSubstringPattern(s);
        System.out.println(repeatedSubstringPattern);


        String aabaaf = "aabaaf";
        int[] ints = conNext(new int[aabaaf.length()], aabaaf);
        System.out.println(Arrays.toString(ints));

    }

    class Solution {

        /**
         * 如果一个串能s被子串重复出现组成 abcabc 由两个abc组成
         * - s+s如果能被s组成 abcabcabcabc能被abcabc组成
         * @param s
         * @return
         */
        public boolean repeatedSubstringPattern(String s) {
            if (s.length() == 1) {
                return false;
            }
            String contact = s.substring(1) + s.substring(0, s.length() - 1);
            if (contact.contains(s)) {
                return true;
            }
            return false;
        }
    }

    public int[] conNext(int[] next, String s) {

        // 1. 初始化
        int j = 0; // 前缀

        // i 为后缀
        for (int i = 1; i < next.length; i++) {
            //如果前缀后缀不相同，不断往前跳前缀
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = next[j - 1];
            }
            //前后缀相同，设置next[i]为next[i-1]+1, 并且j++
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            // 表示j前后相同的部分 aabaa 如果j指向b i指向a，那么i前面经历过两次s.i==s.j 对应j=2，那么next i对应位置的下标就是前缀j=2
            // 实际就是两个指针不断对比，如果一样就继续对比下一个数，如果不一样就让前缀j进行回退
            next[i] = j;
        }
        return next;
    }

    // 如何找到
}
