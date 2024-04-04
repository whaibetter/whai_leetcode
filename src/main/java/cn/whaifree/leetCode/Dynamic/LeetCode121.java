package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/3 14:38
 * @注释
 */
public class LeetCode121 {

    @Test
    public void test()
    {

        int[] prices = {7,1,5,3,6,4};
        System.out.println(new Solution1().maxProfit(prices));
    }



    class Solution {

        /**
         *
         * 只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
         *
         * dp[i][1]表示i天不持有股票的持有现金
         * dp[i][0]表示i天持有股票所有的现金
         * 第i天买入股票现金就是  -prices[i]
         *
         * 第i天有股票 1. 刚刚买入、2. 第i-1天就有
         * 第i天没有股票 2. 第i天卖出 2. 第i-1天就没有股票
         *
         * dp[i][0] 持有股票
         *        - i-1就持有股票，今天不卖 dp[i-1][0]
         *        - 买入股票 -今天的股价 -prices[i]
         *        - max
         * dp[i][1] 无持有
         *        - i-1 保持现状dp[i-1][1]
         *        - 卖出 prices[i] + dp[i-1][0] 价格+[i-1]天加持有的现金
         *        - max
         * dp[0][0] = -price[0]
         * dp[0][1] = 0
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            if (prices.length == 1) {
                return 0;
            }

            int[] dp = new int[2];

            dp[0] = -prices[0];
            dp[1] = 0;
            for (int i = 0; i < prices.length; i++) {
                // 持有股票 1. i-1就有 2. 刚刚买入
                dp[0] = Math.max(dp[0], -prices[i]);
                // 无股票 2. i-1就无，2 刚刚卖出
                dp[1] = Math.max(dp[1], dp[0] + prices[i]);
            }
            return dp[1]; // 最后一天一定没有股票才能有最大利润
        }
    }
    class Solution1 {
        /**
         * 贪心
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            int min = Integer.MAX_VALUE;
            int res = 0;
            for (int i = 0; i < prices.length; i++) {
                min = Math.min(min, prices[i]);
                res = Math.max(res, prices[i] - min);
            }
            return res;
        }
    }


}
