package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/20 22:11
 * @注释
 */
public class LeetCode1049 {

    @Test
    public void test(){
        int[] arr = new int[]{2,7,4,1,8,1};
        int i = lastStoneWeightII1(arr);
        System.out.println(i);
    }

    /**
     *
     * dp[i][j] 表示从0-i中任意取，放入j获取的最大价值
     *
     * half 为背包容量
     *
     *           0 1 2 3 4 5 6 7 8 9 1011
     * index 0:  0 0 2 2 2 2 2 2 2 2 2 2
     * index 1:  0 0 2 2 2 2 2 2 2 2 2 9
     * index 2:  0 0 2 2 2 2 2 2 2 2 2 9
     * index 3:  0 0 2 2 2 2 2 2 2 2 2 9
     * index 4:  0 0 2 2 2 2 2 2 2 2 2 10
     * index 5:  0 0 2 2 2 2 2 2 2 2 2 10
     *
     *
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }

        int half = sum / 2;
        int[][] dp = new int[stones.length][half + 1];
        for (int i = stones[0]; i <= half; i++) {
            dp[0][i] = stones[0];
        }


        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j <= half; j++) {
                // 够放
                if (j >= stones[i]) {
                    // 放入 dp[i-1][j-nums[i]]+value[i]
                    // 不放 dp[i-1][j]
                    dp[i][j] = Math.max(dp[i - 1][j - stones[i]] + stones[i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }


        return sum - dp[stones.length - 1][half] * 2;
    }


    public int lastStoneWeightII1(int[] stones) {
        int sum = 0;
        for (int i = 0; i < stones.length; i++) {
            sum += stones[i];
        }

        int half = sum / 2;
        int[] dp = new int[half + 1];
//        for (int i = stones[0]; i <= half; i++) {
//            dp[i] = stones[0];
//        }

        for (int i = 0; i < stones.length; i++) {
            for (int j = half; j >= stones[i]; j--) {
                // 够放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        /**
         * 如果我们从前往后遍历：
         * for (int j = stones[i]; j <= half; j++) {
         *     dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
         * }
         * 在这种情况下，当我们计算 dp[j] 时，dp[j - stones[i]] 可能已经被当前轮次的更新所影响。
         * 这意味着我们可能会错误地使用当前轮次中已经更新的值，而不是上一轮次的值。这会导致结果不正确。
         *
         * 当我们计算 dp[j] 时，dp[j - stones[i]] 仍然是上一轮次的结果，因为我们在更新 dp[j] 之前还没有更新
         * dp[j - stones[i]]。这确保了我们使用的是正确的历史数据，从而得到正确的结果。
         */

        /**
         * [0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
         * [0, 0, 2, 2, 2, 2, 2, 7, 7, 9, 9, 9]
         * [0, 0, 2, 2, 4, 4, 6, 7, 7, 9, 9, 11]
         * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
         * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
         * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
         */

        return sum - dp[half] * 2;
    }
}
