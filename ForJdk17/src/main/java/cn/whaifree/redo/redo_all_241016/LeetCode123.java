package cn.whaifree.redo.redo_all_241016;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/31 12:03
 * @注释
 */
public class LeetCode123 {

    class Solution {
        /**
         *
         * dp[1] 表示手里没有第一支股票的最大利润
         *      - 前一天就没 dp[i-1][1]
         *      - 前一天有第一支，今天刚刚卖了 dp[i-1][2] + prices[i]
         * dp[2] 表示手里有第一支股票的最大利润
         *      - 前一天就有 dp[i-1][2]
         *      - 前一天没有，刚刚买入第一支 dp[i-1][1] - prices[i]
         *
         * dp[3] 表示手里没有第二支股票的最大利润
         *
         * dp[4] 表示手里有第二支股票的最大利润
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            int[][] dp = new int[prices.length][5];
            dp[0][1] = 0;
            dp[0][2] = -prices[0];
            dp[0][3] = 0;
            dp[0][4] = -prices[0];

            for (int i = 1; i < prices.length; i++) {
                dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][0] - prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] + prices[i]);
                // 手里有第二支股票的最大利润 前一天就有第二支，【前一天没有第二支，也没有第一支但今天买入】
                dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][1] - prices[i]);
                // 手里没有第二支股票的最大利润 前一天就没有第二支，前一天有第二支但今天卖出
                dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][4] + prices[i]);
            }
            return dp[prices.length - 1][3];
        }
    }

}
