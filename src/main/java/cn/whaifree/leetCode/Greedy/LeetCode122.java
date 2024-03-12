package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/25 22:21
 * @注释
 */
public class LeetCode122 {


    @Test
    public void test() {

        System.out.println(new Solution1().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));

        System.out.println(new Solution1().maxProfit(new int[]{1}));


    }

    class Solution {


        /**
         * 随时可以买卖，那么只要有跌的我都不要。
         * 上帝视角：只要涨我就全要
         *
         *  7跌 1涨5 跌 4涨6 跌4
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {

            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
                if (i > 0 && prices[i] - prices[i - 1] > 0) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

    }

    class Solution1 {

        /**
         * dp[i] 表示当天可获得的收益
         * dp[i] = dp[i-1] + if(prices[i-1] < price[i]){prices[i] -  price[i-1]}
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[] dp = new int[prices.length];
            for (int i = 1; i < prices.length; i++) {
                dp[i] = dp[i - 1];
                if (prices[i - 1] < prices[i]) {
                    // 挣钱
                    dp[i] += prices[i] - prices[i - 1];
                }
            }
            return dp[prices.length - 1];
        }
    }
}
