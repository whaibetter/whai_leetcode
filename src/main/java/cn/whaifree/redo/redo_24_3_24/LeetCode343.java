package cn.whaifree.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 17:09
 * @注释
 */
public class LeetCode343 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int i = solution.integerBreak(10);
        System.out.println(i);
    }

    class Solution {
        /**
         * dp[j] 表示 j拆分出来的的最大乘积的最大值
         * - dp[j]
         *      j * i
         *      dp[j-i]+dp[i] 就是对j再进行拆分
         *
         * 10
         * dp
         * 0 1 2  3  4  5  6  7 8 9 10
         *   9 10 21 24 25 25
         * @param n
         * @return
         */
        public int integerBreak(int n) {

            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    int max = Math.max((i - j) * j, dp[i - j] * j);
                    dp[i] = Math.max(dp[i], max);
                }
            }

            return dp[n];
        }
    }

}
