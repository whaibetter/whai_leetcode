package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 15:00
 * @注释
 */
public class LeetCode123 {

    @Test
    public void test() {
        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(new Solution().maxProfit(prices));
    }

    class Solution {
        /**
         * 最多可以完成两笔交易
         * dp[1] 手里有第一支股票，最大利润
         *      - 之前就有
         *      - 之前没有，刚刚买入
         * dp[2] 手里没有第一支股票，手里的最大利润
         *      - 之前就没有
         *      - 刚刚卖出第一支
         * dp[3] 第一支股票卖出，手里有第二支股票的最大利润
         *      - 之前就有
         *      - 之前没有第二支，刚刚买入第二支
         * dp[4] 第一支股票已经卖出，手里没有第二支股票的最大利润
         *      - 之前就没有
         *      - 刚刚卖出第二支
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            int[][] dp = new int[prices.length][5];
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            dp[0][3] = -prices[0];
            dp[0][4] = 0;
            for (int i = 1; i < prices.length; i++) {
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                dp[i][2] = Math.max(dp[i - 1][2], dp[i][1] + prices[i]);
                dp[i][3] = Math.max(dp[i - 1][3], dp[i][2] - prices[i]);
                dp[i][4] = Math.max(dp[i - 1][4], dp[i][3] + prices[i]);
            }
            return dp[prices.length - 1][4];
        }
    }
}
