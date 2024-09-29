package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 1:01
 * @注释
 */
public class LeetCode583 {

    @Test
    public void test() {
        String word1 = "leetcode";
        String word2 = "etco";
        System.out.println(new Solution().minDistance(word1, word2));
    }

    class Solution {

        public int minDistance(String word1, String word2) {
            char[] w1 = word1.toCharArray();
            char[] w2 = word2.toCharArray();
            int[][] dp = new int[w1.length + 1][w2.length + 1];
            for (int i = 0; i <= w2.length; i++) {
                dp[0][i] = i;
            }
            for (int j = 0; j <= w1.length; j++) {
                dp[j][0] = j;
            }
            /**
             *    '' s e a
             * '' 0  1 2 3
             *  e 1  2 1 2  不配 左边|上 +1  配 对i-1 j-1
             *  a 2  3 2
             *  t 3
             *
             * @param word1
             * @param word2
             * @return
             */

            for (int i = 0; i < w1.length; i++) {
                for (int j = 0; j < w2.length; j++) {
                    if (w1[i] == w2[j]) {
                        dp[i + 1][j + 1] = dp[i][j];
                    }else {
                        dp[i + 1][j + 1] = Math.min(dp[i][j + 1], dp[i + 1][j]) + 1;
                    }
                }
            }
            return dp[w1.length][w2.length];
        }
    }
}
