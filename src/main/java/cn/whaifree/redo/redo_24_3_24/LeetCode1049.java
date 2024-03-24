package cn.whaifree.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 15:09
 * @注释
 */
public class LeetCode1049 {


    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] stones = new int[]{31,26,33,21,40};
        int i = solution.lastStoneWeightII(stones);
        System.out.println(i);
    }

    class Solution {
        /**
         * 背包问题
         *
         * dp[i][j] 表示0-i物品里选择 放入j背包
         *
         * 石头的总重量的一半为目标，找到几块石头可以尽可能满足half
         *
         *
         *
         * @param stones
         * @return
         */
        public int lastStoneWeightII(int[] stones) {

            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }

            int half = sum / 2; //背包容量
            int[][] dp = new int[stones.length][half + 1];
            /**
             * 2 3 4 5 3
             *
             *   0 1 2 3 4
             * 0 0 0 2 2 2
             * 1
             * 2
             * 3
             * 4
             *
             * 1. 放入物品i  dp[i][j] = dp[i][j-weight[i]]+values[i]
             * 2. 不放物品i           = dp[i-1][j]
             *
             */

            for (int i = stones[0]; i <= half; i++) {
                dp[0][i] = stones[0];
            }

            for (int i = 1; i < stones.length; i++) {
                for (int j = 1; j <= half; j++) {
                    if (stones[i] > j) {
                        // 放不下
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i - 1][j - stones[i]] + stones[i], dp[i - 1][j]);
                    }
                }

            }

            return sum - 2 * dp[stones.length - 1][half];
        }
    }
}
