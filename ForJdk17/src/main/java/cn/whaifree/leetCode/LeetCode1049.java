package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/18 14:31
 * @注释
 */
public class LeetCode1049 {

    @Test
    public void test() {
        int[] stones = {1,2};
        int result = new Solution1().lastStoneWeightII(stones);
        System.out.println(result);
    }

    class Solution {
        /**
         * 背包容量为half，让其尽可能装满，除非装不下了。到装不下了，这一部分已经装入的就是拿来碰撞的。剩下就是sum-dp[half]-dp[half]
         *
         * 目标就是让石头的总价值分为两部分
         *
         * 转换为背包问题
         *
         * dp[j] 表示 容量为j的背包最多能够容纳多少价值的物品
         * - 物品i的价值 为 stones[i]
         * - 物品i的重量 为 stones[i]
         *
         * 1 <= stones.length <= 30
         * 1 <= stones[i] <= 100
         * 初始化为store[half]
         *
         *
         * 递推公式 dp[j] = Math.max(dp[j],dp[j-weight[i]]+value[i])
         *          分成两堆石头，一堆石头的总重量是dp[half]，另一堆就是sum - dp[half]。
         *
         * @param stones
         * @return
         */
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }

            int half = sum / 2;
            int[] dp = new int[half + 1];
            for (int i = 0; i < stones.length; i++) {
                for (int j = half; j >= stones[i]; j--) { // 确保背包能够放下
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }

            //  dp[half] 是要被消灭的，那两个部分的消灭就*2
            return sum - dp[half] * 2;
        }
    }

    class Solution1 {
        /**
         * half = sum/2
         *
         *
         * dp[i][j] 表示 放入i后，容量为j的背包的最大价值
         *
         * 1. 放入i  dp[i][j] = dp[i-1][j-weight[i]]+value[i]
         * 2. 不放入i dp[i][j] = dp[i-1][j]
         *
         *  2 4 1 1 target=4
         *
         *   0 1 2 3 4
         * 0 0 0 2 2 2
         * 1 0 0 2 2 2
         * 2 0 1 3 3 3
         * 3 0 1 4 4 4
         *
         * @param stones
         * @return
         */
        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int stone : stones) {
                sum += stone;
            }
            // 计算所有石头的总重量，并将其的一半作为目标重量
            int half = sum / 2;

            int length = stones.length;
            int[][] dp = new int[length][half + 1];

            for (int i = stones[0]; i <= half; i++) {
                dp[0][i] = stones[0];
            }
            for (int i = 1; i < length; i++) {
                for (int j = 1; j <= half ; j++) {
                    if (j < stones[i]) {
                        // 放不下 就不放了，价值为i-1物品的最大价值
                        // 当前石头重量大于目标重量，无法放入，价值等于前i-1个石头的最大价值
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        // 取放入和不放入当前石头两种情况中的最大价值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                    }
                }
            }

            // 返回石头总重量减去最接近目标重量的那堆石头的两倍重量
            return sum - dp[length - 1][half] * 2;
        }
    }
}
