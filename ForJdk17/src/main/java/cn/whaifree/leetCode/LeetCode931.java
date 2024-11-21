package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 11:59
 * @注释
 */
public class LeetCode931 {

    @Test
    public void test() {
        Solution solution = new Solution();
        // [[2,1,3],[6,5,4],[7,8,9]]
        System.out.println(solution.minFallingPathSum(new int[][]{{2, 1}, {6, 5}}));
    }

    class Solution {
        /**
         * int[i][j] 表示从最顶到i j 的价值
         *
         * 第一层为对应格子的值
         *
         * 最最左边和最右边只能由 2个推出
         *
         * @param matrix
         * @return
         */
        public int minFallingPathSum(int[][] matrix) {
            int n = matrix.length;
            int[][] dp = new int[n][n];
            // 第一层
            for (int i = 0; i < matrix.length; i++) {
                dp[0][i] = matrix[0][i];
            }

            for (int i = 1; i < n; i++) {
                // 每层 ， 第一个元素只能由上一个
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1])+ matrix[i][0];
                for (int j = 1; j < n - 1; j++) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + matrix[i][j];
                }
                dp[i][n - 1] = Math.min(dp[i - 1][n - 1], dp[i - 1][n - 2]) + matrix[i][n - 1];
            }

            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                min = Math.min(min, dp[n - 1][i]);
            }
            return min;
        }
    }

}
