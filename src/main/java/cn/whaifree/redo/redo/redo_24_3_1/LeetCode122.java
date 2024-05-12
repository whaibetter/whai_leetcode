package cn.whaifree.redo.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 11:16
 * @注释
 */
public class LeetCode122 {

    @Test
    public void test() {
        System.out.println(new Solution().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    class Solution {
        public int maxProfit(int[] prices) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++) {
                int sub = prices[i] - prices[i - 1];
                if (sub > 0) {
                    profit += sub;
                }
            }
            return profit;
        }
    }

}
