package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/4 18:30
 * @注释
 */
public class LeetCode122 {

    @Test
    public void test()
    {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit1(prices));

        System.out.println(new Solution().maxProfit(prices));
    }

    public int maxProfit(int[] prices)
    {
        int nowProfit = 0;
        int right = 1;
        while (right < prices.length) {
            if (prices[right] > prices[right - 1]) {
                nowProfit += prices[right] - prices[right - 1];
            }
            right++;
        }
        return nowProfit;
    }

    public int maxProfit1(int[] prices)
    {
        int nowProfit = 0;
        int right = 1;
        int left = 0;
        while (right < prices.length) {
            while (left < right && prices[left] > prices[right]) {
                left++;
            }
            if (prices[left] < prices[right]) {
                nowProfit += (prices[right] - prices[left]);
                left = right;
            }
            right++;
        }
        return nowProfit;
    }

    class Solution {
        /**
         * dp[i][0] 表示第i天手头没有股票的最大利润
         *      - 刚卖 dp[i-1][1]+price[i]
         *      - 前一天就没有 dp[i-1][0]
         * do[i][1] 表示第i天手头有股票的最大利润
         *      - 前一天就有  dp[i-1][1]
         *      - 刚买入 dp[i-1][0] - price[i]
         *
         *   [7,1,5,3,6,4]
         * 0  0 0 4 4
         * 1 -7-1-1
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[prices.length - 1][0];
        }
    }
}
