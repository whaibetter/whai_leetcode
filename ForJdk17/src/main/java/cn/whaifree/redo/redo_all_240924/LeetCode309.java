package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 14:48
 * @注释
 */
public class LeetCode309 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] prices = {2, 1, 3, 0, 2};
        System.out.println(solution.maxProfit(prices));
    }

    class Solution {

        /**
         * dp[i][0] 为第i天手头没有股票的最大利润
         * - 前一天就没有 dp[i-1][0]
         * - 刚刚卖出 dp[i-1][1]+value[i]
         * dp[i][1] 表示第i天手头有股票的最大利润
         * - 前一天就有 dp[i-1][1]
         * - 刚刚买入 dp[i-2][0]-value[i]
         *
         * dp[0][0] = 0;
         * dp[0][1] = -prices[0]
         * dp[1][0] = 0;
         * dp[1][1] = -math.min(prices[1],prices[0])
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            if (prices.length <= 1) {
                return 0;
            }

            int[][] dp = new int[prices.length][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[1][0] = Math.max(0, dp[0][1] + prices[1]);
            dp[1][1] = -Math.min(prices[1], prices[0]);

            for (int i = 2; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
}
