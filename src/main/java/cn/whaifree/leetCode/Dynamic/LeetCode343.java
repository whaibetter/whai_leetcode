package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/11 11:14
 * @注释
 */
public class LeetCode343 {

    @Test
    public void test() {
        System.out.println(new Solution().integerBreak(10));
    }

    class Solution {
        /**
         * dp[i] 表示 拆分出i后的乘积的最大值
         *      dp[i] = 遍历 dp[i-j] * i 或者 i * j
         *      dp[i] 可能为两个数相乘，或者是多个数相乘（这时就需要遍历）。
         * 初始化 dp[0] = null；dp[1] = 1;
         *
         * [0 1 2 3 4 5 6 7   8  9 10]
         * [1 1 1 2 4 6 9 12 16 27 36]
         *
         *
         * @param n
         * @return
         */
        public int integerBreak(int n) {

            int[] dp = new int[n + 1];
//            dp[0] = 1;
            dp[2] = 1; // 表示数字2可以划分为1+1 1*1为最大值
            for (int i = 2; i < n+1; i++) {
                for (int j = 1; j < i - 1; j++) {
                    // Math.max((i - j) * j, dp[i - j] * j) 表示两个数相乘 和使用 前面的数相乘
                    dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
                }
            }

            return dp[n];
        }
    }
}
