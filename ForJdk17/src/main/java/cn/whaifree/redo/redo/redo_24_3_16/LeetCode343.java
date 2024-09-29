package cn.whaifree.redo.redo.redo_24_3_16;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 16:59
 * @注释
 */
public class LeetCode343 {

    class Solution {

        /**
         *  dp[j] 表示拆分出j后的乘积最大值
         *
         *  dp[j]=
         *  (j-i)*j 两个数相乘 或
         *  dp[j-i] * i
         *
         *  dp[j] = dp[j-1] *
         * @param n
         * @return
         */
        public int integerBreak(int n) {

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                for (int j = 1; j < i - 1; j++) {
                    dp[i] = Math.max(
                            dp[i],
                            Math.max((i - j) * j, dp[i - j] * j)
                    );
                }
            }

            return dp[n];
        }
    }
}
