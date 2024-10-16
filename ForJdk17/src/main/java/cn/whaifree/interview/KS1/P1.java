package cn.whaifree.interview.KS1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/16 13:53
 * @注释
 */
public class P1 {
    @Test
    public void test() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int left = 0;
        int right = 0;
        while (right < prices.length) {
            if (prices[right] < prices[left]) {
                left = right;
            }else {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
            }
            right++;
        }
        return maxProfit;
    }
}
