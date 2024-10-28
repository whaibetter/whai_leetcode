package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/26 15:37
 * @注释
 */
public class LeetCode518 {

    @Test
    public void test() {
        int[] coins = {1, 2, 5};
        int amount = 5;
        int i = new Solution2().change(amount, coins);
        System.out.println(i);
    }

    class Solution {
        /**
         * dp[j] 表示前i个硬币容量为j的背包能够装入的组合数
         *
         * dp[j] = dp[j-coins[i-1]] + 1
         *
         * @param amount
         * @param coins
         * @return
         */
        public int change(int amount, int[] coins) {


            int[] dp = new int[amount + 1];

            dp[0] = 1;
            for (int i = 1; i <= coins.length; i++) {
                for (int j = coins[i - 1]; j <= amount; j++) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]] ;
                }
            }

            return dp[amount];

        }
    }

    class Solution2{

        /**
         * 二维数组
         * @param amount
         * @param coins
         * @return
         */
        public int change(int amount, int[] coins) {
            int n = coins.length;
            int[][] dp = new int[n + 1][amount + 1];

            // 初始化第一行，即不使用任何硬币时，只有金额为0的方法数为1
            for (int j = 0; j <= amount; j++) {
                dp[0][j] = 0;
            }
            dp[0][0] = 1;

            // 初始化第一列，即凑齐金额为0的方法数都是1（不使用任何硬币）
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 1;
            }

            // 动态规划填表
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= amount; j++) {
                    // 不使用第i种硬币
                    dp[i][j] = dp[i - 1][j];

                    // 如果当前金额j大于等于第i种硬币的面值，那么可以考虑使用这种硬币
                    // 使用第i种硬币的方法数等于不使用第i种硬币的方法数加上使用第i种硬币后的方法数
                    if (j >= coins[i - 1]) {
                        dp[i][j] += dp[i][j - coins[i - 1]];
                    }
                }
            }

            return dp[n][amount];
        }
    }


}
