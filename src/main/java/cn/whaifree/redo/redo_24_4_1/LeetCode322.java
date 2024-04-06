package cn.whaifree.redo.redo_24_4_1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 16:28
 * @注释
 */
public class LeetCode322 {
    @Test
    public void test() {
        int[] coins = { 2 };
        int amount = 3;
        Solution solution = new Solution();
        int i = solution.coinChange(coins, amount);
        System.out.println(i);
    }

    class Solution {
        /**
         * 背包容量amount+1
         *
         * dp[j] 表示从0-i中任意取放入amount
         * dp[j] = math.min(dp[j] , dp[j-coins[i]]+1)
         *
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j < amount + 1; j++) {
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }
    }
}
