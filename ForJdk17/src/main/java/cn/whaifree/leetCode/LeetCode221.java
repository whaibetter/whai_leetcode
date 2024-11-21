package cn.whaifree.leetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 12:20
 * @注释
 */
public class LeetCode221 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.maximalSquare(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
    }

    class Solution {
        /**
         * 如果三个位置都为x，那么本节点就是变长为x的
         *
         * @param matrix
         * @return
         */
        public int maximalSquare(char[][] matrix) {
            int maxLen = 0;
            int[][] dp = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == '1') {
                    dp[0][i] = 1;
                    maxLen = 1;
                }
            }

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == '1') {
                    dp[i][0] = 1;
                    maxLen = 1;
                }
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    if (matrix[i - 1][j] == '1' && matrix[i][j - 1] == '1' && matrix[i - 1][j - 1] == '1') {
                        if (matrix[i][j] == '1') {
                            dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                        }
                    } else if (matrix[i][j] == '1') {
                        dp[i][j] = 1;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
            return maxLen * maxLen;
        }
    }
}
