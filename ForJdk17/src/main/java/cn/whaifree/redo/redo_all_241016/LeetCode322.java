package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 15:12
 * @注释
 */
public class LeetCode322 {

    @Test
    public void test() {
        int[] coins = {2, 5};
        int amount = 12;
        Solution solution = new Solution();
        int result = solution.coinChange(coins, amount);
        System.out.println(result);
    }

    class Solution {
        /**
         * 无穷背包
         *   0 1 2 3 4 5 6 7 8 9 10 11
         * 1 1 1 2 3 4 5 6 7 8 9 10 11
         * 2 1 1 1 2
         * 5
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            for (int j = 1; j <= amount; j++) {
                for (int i = 0; i < coins.length; i++) {
                    if (j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
            return dp[amount] == Integer.MAX_VALUE? -1 : dp[amount];
        }
    }

}
