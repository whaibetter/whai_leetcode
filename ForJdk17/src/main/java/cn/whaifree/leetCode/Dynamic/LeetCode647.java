package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/22 12:36
 * @注释
 */
public class LeetCode647 {

    class Solution {
        /**
         * 判断一个子字符串（字符串的下表范围[i,j]）是否回文，
         * 依赖于子字符串（下表范围[i + 1, j - 1]）） 是否是回文。
         *
         * 布尔类型的dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串
         *
         * ij两个循环，能够覆盖所有循环
         * - dp[i][j] 判断是否回文的时候，只需要dp[i+1][j-1]是否回文，并且s[i]==s[j]
         * 当s[i]=s[j]
         * 1. i=j  回文
         * 2. i+1=j 回文
         * 3. dp[i+1][j-1]==true 回文
         *
         *
         *   <img src="https://code-thinking-1253855093.file.myqcloud.com/pics/20210121171032473-20230310132134822.jpg" alt="">
         * dp[i][j]由dp[i+1][j-1]过来，所以必须从下到上，左到右
         *
         *  @param s
         * @return
         */
        public int countSubstrings(String s) {

            char[] chars = s.toCharArray();

            boolean[][] dp = new boolean[chars.length][chars.length];
            int res = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                for (int j = i; j < s.length(); j++) {
                    if (chars[i] == chars[j]) {
                        if (j - i == 1|| i==j || dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                            res++;
                        }
                    }
                }
            }


            return res;
        }

    }


    class Solution1 {
        public int countSubstrings(String s) {
            // 中心点向外扩充
            // 1个元素可以作为中心点
            // 2个元素也能作为中心点
            char[] chars = s.toCharArray();
            int res = 0;
            for (int i = 0; i < chars.length; i++) {
                res += subString(chars, i, i);
                res += subString(chars, i, i + 1);
            }
            return res;
        }

        /**
         *
         * @param chars
         * @param center1 向左扩展的指针
         * @param center2 向右扩展的指针
         * @return 有几个子串
         */
        public int subString(char[] chars, int center1, int center2) {
            int res = 0;
            while (center1 >= 0 && center2 < chars.length && chars[center1] == chars[center2]) {
                center1--;
                center2++;
                res++;
            }
            return res;
        }
    }

    @Test
    public void test()
    {
        Solution1 solution = new Solution1();
        String s = "aaa";
        int res = solution.countSubstrings(s);
        System.out.println(res);
    }
}
