package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 23:07
 * @注释
 */
public class LeetCode309 {

    class Solution {

        /**
         * dp[][]
         *
         *
         *
         *    1 2 3 0 2
         * 0  0 1 2
         * 1  0 0 0
         * 2 -1-1
         * 3 -1-2
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            int n = prices.length;
            if (n < 2) {
                return 0;
            }
            int[][] dp = new int[n][3];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
                dp[i][2] = dp[i - 1][1] + prices[i];
            }

            return 1;
        }
    }

    @Test
    public void test() {
        int b = 1 << 4;
        Solution solution1 = new Solution();
        int i1 = solution1.hashCode();
        String binaryString = Integer.toBinaryString(i1);
        System.out.println(binaryString);

        int[] prices = {1,0};
        Solution1 solution = new Solution1();
        int i = solution.maxProfit(prices);
        System.out.println(i);
    }

    class Solution1 {
        /**
         * 1. 当天有股票 0
         *      - 前一天就有 dp[0][i-1]
         *      - 当天买入 dp[1][i-2] - values[i]
         * 2. 当天没股票 1
         *      - 刚刚卖 dp[0][i-1]+value[i]
         *      - 前一天就没有 dp[1][i-1]
         *
         * dp[0][0] = -values[0] 第一天
         * dp[1][0] = 0
         *
         * dp[0][1] = dp[0][0] dp[1][0]-values[i] 第二天有股票，可能第一天就有，或者刚刚买入
         * dp[1][1] = dp[1][0] dp[0][1]+values[i]
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            if (prices.length < 2) {
                return 0;
            }

            int[][] dp = new int[2][prices.length];

            dp[0][0] = -prices[0];
            dp[1][0] = 0;
            dp[0][1] = Math.max(dp[0][0], dp[1][0] - prices[1]);
            dp[1][1] = Math.max(dp[1][0], dp[0][1] + prices[1]);
            for (int i = 2; i < prices.length; i++) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 2] - prices[i]);
                dp[1][i] = Math.max(dp[0][i - 1] + prices[i], dp[1][i - 1]);
            }
            return dp[1][prices.length - 1];
        }
    }
}
