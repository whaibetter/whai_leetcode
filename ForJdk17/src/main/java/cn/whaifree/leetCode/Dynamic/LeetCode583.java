package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/5/2 11:14
 * @注释
 */
public class LeetCode583 {
    @Test
    public void test()
    {

        //  "leetcode", word2 = "etco"
        int i = new Solution1().minDistance("leetcode", "etco");
        System.out.println(i);
    }

    class Solution {
        /**
         * 最长子序列的长度
         * word1.len - dp
         * word2.len - dp
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {
            /**
             * dp[i][j] 表示 word1的0-i word2的0-j 最长公共子序列的长度
             *
             *    '' s e a
             * ''  0 0 0 0
             * e   0 0 1 1
             * a   0 0 1 2
             * t   0 0 1 2
             *
             */

            int len2 = word2.length();
            int len1 = word1.length();
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return len1 + len2 - (dp[len1][len2] << 1);
        }

    }

    class Solution1 {
        /**
         * dp[i][j]：以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数。
         *    '' s e a
         * ''  0 1 2 3
         * e   1 2 1 2
         * a   2 3 2 1
         * t   3 4 3 2
         * 等 dp[i-1][j-1]
         * 不等 min(dp[i-1][j], dp[i][j-1]) + 1
         * 1. 删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
         * 2. 删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
         * 3. 同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
         *          dp[i][j - 1] + 1 = dp[i - 1][j - 1] + 2
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {
            int len2 = word2.length();
            int len1 = word1.length();
            int[][] dp = new int[len1 + 1][len2 + 1];

            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
            return dp[len1][len2];
        }
    }
}
