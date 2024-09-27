package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 11:01
 * @注释
 */
public class LeetCode1049 {

    @Test
    public void test() {
        int[] stones = {2,7,4,1,8,1};
        System.out.println(new Solution1().lastStoneWeightII(stones));
    }

    class Solution {
        /**
         * 0 - 1 背包
         * <p>
         * 背包容量sumStones/2
         * <p>
         * dp[i][j] 表示 从0-i任意选择石头，放入容量为j的背包的最大价值
         * 剩余的石头就是sumStones/2- dp[i][j]
         *
         * @param stones
         * @return
         */
        public int lastStoneWeightII(int[] stones) {
            int sumStones = Arrays.stream(stones).sum();
            int pkgSize = sumStones / 2;
            int[][] dp = new int[stones.length][pkgSize + 1];
            for (int i = stones[0]; i <= pkgSize; i++) {
                dp[0][i] = stones[0];
            }
            for (int i = 1; i < stones.length; i++) {
                for (int j = 0; j <= pkgSize; j++) {
                    // 放得下
                    if (stones[i] <= j) {
                        dp[i][j] = Math.max(dp[i - 1][j - stones[i]] + stones[i], dp[i - 1][j]);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return sumStones - 2 * dp[stones.length - 1][pkgSize];

        }
    }

    class Solution1 {

        public int lastStoneWeightII(int[] stones) {
            int sum = 0;
            for (int i : stones) {
                sum += i;
            }
            int pkgSize = sum / 2;

            int[] dp = new int[pkgSize + 1];
            for (int i = 0; i < stones.length; i++) {
                for (int j = pkgSize; j >= stones[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                }
            }
            return sum - 2 * dp[pkgSize];
        }


    }
}
