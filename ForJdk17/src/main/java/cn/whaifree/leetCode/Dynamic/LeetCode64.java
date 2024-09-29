package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/16 11:22
 * @注释
 */
public class LeetCode64 {

    @Test
    public void test()
    {
        int[][] grid = new int[][] { {1,2,3}, {4,5,6} };
        Solution solution = new Solution();
        int i = solution.minPathSum(grid);
        System.out.println(i);
    }

    class Solution {
        /**
         * dp
         * int[i][j]表示到该点所需的最最小数字总和
         *
         * @param grid
         * @return
         */
        public int minPathSum(int[][] grid) {

            int h = grid.length;
            int w = grid[0].length;
            int[][] dp = new int[h][w];
            dp[0][0] = grid[0][0];

            // dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j]

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    if (j == 0 && i != 0) {
                        dp[i][j] = dp[i - 1][j] + grid[i][j];
                        continue;
                    }
                    if (i == 0 && j != 0) {
                        dp[i][j] = dp[i][j - 1] + grid[i][j];
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[h - 1][w - 1];
        }
    }

}
