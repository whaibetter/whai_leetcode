package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/9 12:04
 * @注释
 */
public class LeetCode63 {

    @Test
    public void test() {
        int[][] ints = new int[][]{{0,0,0,0},{0,1,0,0},{0,0,0,0}};
        System.out.println(new Solution().uniquePathsWithObstacles(ints));
    }

    class Solution {
        /**
         *  dp[i][j] 表示 i,j出可能的路径
         *  dp[i][j] = dp[i-1][j] + dp[i][j-1] 且如果该点有障碍，直接continue
         *  初始化 dp【0,0】=0
         * @param obstacleGrid
         * @return
         */
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            if (obstacleGrid[0][0] == 1) {
                return 0;//起点就有障碍，不可能过去
            }

            int h = obstacleGrid.length;
            int w = obstacleGrid[0].length;
            int[][] dp = new int[h][w];
            dp[0][0] = 1;
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }

                    if (obstacleGrid[i][j] == 0) {
                        int tmp = 0;
                        if (i >= 1) tmp += dp[i - 1][j];
                        if (j >= 1) tmp += dp[i][j - 1];
                        dp[i][j] = tmp;
                    }
                }
            }

            return dp[h-1][w-1];
        }
    }
}
