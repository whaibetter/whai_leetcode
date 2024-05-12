package cn.whaifree.redo.redo.redo_24_4_6;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 13:42
 * @注释
 */
public class LeetCode121 {
    @Test
    public void test() {
        int[] prices = {7};
        int res = new Solution1().maxProfit(prices);
        System.out.println(res);
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int min = Integer.MAX_VALUE;
            int res = 0;
            for (int price : prices) {
                min = Math.min(min, price);
                res = Math.max(res, price - min);
            }
            return res;
        }
    }

    class Solution1 {
        /**
         *
         *
         * dp[i][2]
         * dp[i][0]表示第i天手头没有股票的手头现金
         *      第i天卖出了 dp[i-1][1]+price[i]
         *      第i-1天就没有股票  dp[i-1][0]
         * dp[i][1]表示第i天手头有股票的手头现金
         *      第i天买入的   -price[i]
         *      第i-1天持有的   dp[i-1][1]
         *
         * 初始化
         * dp[0][1] = -price[0]
         * dp[0][0] = 0
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][2];
            dp[0][1] = -prices[0];
            dp[0][0] = 0;
            for (int i = 1; i < prices.length; i++) {
                dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
}
