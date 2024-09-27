package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 15:35
 * @注释
 */

public class LeetCode322 {

    @Test
    public void test() {
        int[] coins = new int[]{2,5,10,1};
        int amount = 27;
        System.out.println(new Solution().coinChange(coins, amount));
    }

    class Solution {
        /**
         * 从0-i中任意选择，凑满amount的最少硬币数量
         * 0 1 2 3 4 5 6 7 8 9 10 11
         * 2 1 - 1 - 2 - 3 - 4 - 4  -
         * 1 1 1 1
         * 5
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {

            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);

            for (int i = coins[0]; i <= amount; i += 1) {
                if (i % coins[0] == 0) {
                    dp[i] = i / coins[0];
                }
            }
            dp[0] = 0;
            for (int i = 1; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount; j++) {
                    int X = dp[j - coins[i]];
                    if (X != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], X + 1);
                    }
                }
            }
            return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
        }
    }


}
