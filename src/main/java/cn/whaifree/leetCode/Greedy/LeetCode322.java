package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/25 22:21
 * @注释
 */
public class LeetCode322 {


    @Test
    public void test() {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
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

        // TODO 动态规划
    }
}
