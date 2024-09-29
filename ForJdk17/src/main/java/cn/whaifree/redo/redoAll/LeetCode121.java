package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 15:09
 * @注释
 */
public class LeetCode121 {
    @Test
    public void test() {

        Solution1 solution = new Solution1();
        int i = solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        System.out.println(i);

    }


    class Solution {
        public int maxProfit(int[] prices) {

            int maxProfit = 0;
            int left = 0;
            int right = 0;
            while (right < prices.length) {
                if (prices[right] < prices[left]) {
                    left = right;
                }
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
                right++;
            }
            return maxProfit;
        }
    }

    class Solution1 {
        /**
         *
         * 只能选择 某一天 买入这只股票
         *
         *
         * dp[0][i] 表示第i天手头没有有股票的最大利润
         *      - 第i-1天就没有 dp[0][i-1]
         *      - 今天刚刚卖出  dp[1][i-1] + price[i]
         * dp[1][i] 表示第i天手头有股票的最大利润
         *      - 前一天就有  dp[1][i-1]
         *      - 刚刚买入   - price[i] 只能选择 某一天 买入这只股票
         *
         *
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            int[][] dp = new int[2][prices.length];

            dp[0][0] = 0;
            dp[1][0] = -prices[0];
            for (int i = 1; i < prices.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + prices[i]);
                dp[1][i] = Math.max(dp[1][i - 1], - prices[i]);
            }
            return dp[0][prices.length - 1];
        }
    }
}
