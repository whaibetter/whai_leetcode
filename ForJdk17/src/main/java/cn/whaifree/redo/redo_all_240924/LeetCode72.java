package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/1 13:10
 * @注释
 */
public class LeetCode72 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.minDistance("horse", "ros"));
    }

    class Solution {
        /**
         *    ''  h o r s e
         * '' 0   1 2 3 4 5
         * r  1   1 2 2 3 4
         * o  2   2 1 2 3 4
         * s  3   3 2 2 2 3
         *
         * 相同，i-1 j-1
         *
         * 不同 min
         * 替换 i-1 j-1 +1
         * 增加 i j-1 +1
         * 删除 i-1 j +1
         *
         * @param word1
         * @param word2
         * @return
         */
        public int minDistance(String word1, String word2) {
            if (word2.length() > word1.length()) {
                return minDistance(word2, word1);
            }
            int[][] dp = new int[word2.length() + 1][word1.length() + 1];
            for (int i = 0; i <= word1.length(); i++) {
                dp[0][i] = i;
            }
            for (int i = 0; i <= word2.length(); i++) {
                dp[i][0] = i;
            }

            for (int i = 1; i <= word2.length(); i++) {
                for (int j = 1; j <= word1.length(); j++) {
                    if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                }
            }
            return dp[word2.length()][word1.length()];
        }
    }
}
