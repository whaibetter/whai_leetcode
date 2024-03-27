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
        int[] coins = {1,2,5};
        int amount = 11;
        int i = new Solution().coinChange(coins, amount);
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

}
