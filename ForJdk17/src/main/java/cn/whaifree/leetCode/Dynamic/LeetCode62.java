package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * TODO 还没写完
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/8 12:58
 * @注释
 */
public class LeetCode62 {

    @Test
    public void test(
    ) {
        System.out.println(new Solution().uniquePaths(3, 3));
    }

    class Solution {
        /**
         * 确定dp[i,j] 该点可能的路径数
         * 递推公式 dp[i,j]= dp[i-1,j] + dp[i,j-1]
         * 初始化 dp[0,0]=1
         * 推
         *  0 1 1 1 1 1 1
         *  1 2 3 4 5 6 7
         *  1 3 6 10 15 21 28
         * @param m
         * @param n
         * @return
         */
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            dp[0][0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i>=1) dp[i][j] += dp[i - 1][j];
                    if (j>=1) dp[i][j] += dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
