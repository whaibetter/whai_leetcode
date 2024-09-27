package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 15:04
 * @注释
 */
public class LeetCode279 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int i = solution.numSquares(13);
        System.out.println(i);
    }

    class Solution {
        /**
         * 背包容量n
         *
         * 物品 1 4 9 16
         *
         *  任意背包
         * @param n
         * @return
         */
        public int numSquares(int n) {

            int sqrt = (int) Math.sqrt(n);

            int[] dp = new int[n + 1];
            for (int i = 1; i < dp.length; i++) {
                dp[i] = i;
            }

            for (int i = 2; i <= sqrt; i++) {
                for (int j = 0; j <= n; j++) {
                    int size = i * i;
                    if (size <= j) {
                        dp[j] = Math.min(dp[j], dp[j - size] + 1);
                    }
                }
            }
            return dp[n];
        }
    }
}
