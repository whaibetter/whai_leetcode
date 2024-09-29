package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/21 23:53
 * @注释
 */
public class LeetCode322 {

    @Test
    public void test() {
        int[] coins = {2};
        int amount = 3;
        Solution solution = new Solution();
        int i = solution.coinChange(coins, amount);
        System.out.println(i);
    }

    class Solution {

        /**
         * dp[j] 表示从0-i中随便取，满足j个背包容量的最少个数
         *
         *    0  1  2  3  4  5  6  7  8  9  10 11
         * 1 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11]
         * 2 [0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6]
         * 5 [0, 1, 1, 2, 2, 1, 2, 2, 3, 3, 2, 3]
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {


            int[] dp = new int[amount + 1];
            for (int i = 1; i <= amount; i++) {
                dp[i] = Integer.MAX_VALUE;
            }
            dp[0] = 0;
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount; j++) {
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }
    }
}
