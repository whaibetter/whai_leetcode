package cn.whaifree.redo.redo_all_241121;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 10:41
 * @注释
 */
public class LeetCode343 {

    class Solution {
        /**
         *
         * k>=2
         *
         * dp[i] 表示 吧n拆为i和j-i的乘积的最大值
         *      = i * (j-i) 或者 dp[j-i] * i
         *
         * n=10
         *
         * 0 1 2  3  4  5  6  7  8  9 10
         *
         *
         * @param n
         * @return
         */
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            // dp[i] 可以来自` j * (i-j) `或` dp[i-j] * j`
            for (int right = 2; right <= n; right++) {
                int max = Integer.MIN_VALUE;
                for (int left = 0; left < right; left++) { // 7
                    max = Math.max(max, left * (right - left));  // 7 = 3 + 4
                    max = Math.max(max, dp[right - left] * left); // dp[4] * 3
                }
                dp[right] = max;
            }
            return dp[n];
        }
    }
}
