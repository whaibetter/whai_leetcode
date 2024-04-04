package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/27 12:58
 * @注释
 */
public class LeetCode322 {
    @Test
    public void test() {
        int[] coins = {1};
        int amount = 0;
        int i = new Solution1().coinChange(coins, amount);
        System.out.println(i);
    }

    class Solution {
        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);

            dp[0] = 0; // 总和为0的方案为0
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount ; j++) {
                    //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                    if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                        //选择硬币数目最小的情况
                        // 不放 和 放（加上本次方案1）
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }

            /**
             * 输入：coins = [2], amount = 3
             * 输出：-1
             */
            return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
        }
    }


    class Solution1 {
        /**
         * 背包容量为amount
         * coins里随便取
         *
         * dp[j] 表示使用[0-i]任意硬币取凑出j的最小硬币数
         *
         * [1, 2, 5]
         *   0 1 2 3 4 5 6
         * 0 0 1 2 3 4 5 6
         * 1 0 1 1 2 2 3 3
         * 2
         * 3
         * 4
         *
         * dp[j] = math.min(dp[j],dp[j-coin[i]]+1)
         *
         * @param coins
         * @param amount
         * @return
         */
        public int coinChange(int[] coins, int amount) {


            int[] dp = new int[amount+1];


            for (int i = 1; i < dp.length; i++) {
                dp[i] = Integer.MAX_VALUE;
            }


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
