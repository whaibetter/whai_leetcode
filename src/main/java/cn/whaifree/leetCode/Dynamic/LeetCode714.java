package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/19 12:27
 * @注释
 */
public class LeetCode714 {

    @Test
    public void test()
    {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int maxProfit = new Solution1().maxProfit(prices, 2);
        System.out.println(maxProfit);
    }

    class Solution {
        /**
         * dp[i][0] 表示手里没有股票的最大手头持有
         * - i-1 就没有 dp[i-1][0]
         * - i刚刚卖出 dp[i-1][1] + price[i] - fee
         * dp[i][1] 表示手里有股票的最大手头持有
         * - i-1 有 dp[i-1][1]
         * - i刚刚买入 dp[i-1][0] - price[i] - fee
         * @param prices
         * @param fee
         * @return
         */
        public int maxProfit(int[] prices, int fee) {
            int[][] dp = new int[prices.length][2];
            dp[0][1] = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
            }
            return dp[prices.length - 1][0];
        }
    }

    class Solution1 {
        /**
         * dp[i][0] 表示手里没有股票的最大手头持有
         * - i-1 就没有 dp[i-1][0]
         * - i刚刚卖出 dp[i-1][1] + price[i] - fee
         * dp[i][1] 表示手里有股票的最大手头持有
         * - i-1 有 dp[i-1][1]
         * - i刚刚买入 dp[i-1][0] - price[i] - fee
         * @param prices
         * @param fee
         * @return
         */
        public int maxProfit(int[] prices, int fee) {
            int[] dp = new int[2];
            dp[1] = -prices[0] - fee;
            for (int i = 1; i < prices.length; i++) {
                dp[0] = Math.max(dp[0], dp[1] + prices[i]);
                dp[1] = Math.max(dp[1], dp[0] - prices[i] - fee);
            }
            return dp[0];
        }
    }



}
