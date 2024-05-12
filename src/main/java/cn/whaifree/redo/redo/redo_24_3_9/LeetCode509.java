package cn.whaifree.redo.redo.redo_24_3_9;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 12:07
 * @注释
 */
public class LeetCode509 {
    @Test
    public void test() {
        int result = new Solution().fib(4);
        System.out.println(result);
    }

    class Solution {

        /**
         *  含义
         *  递推 F(n) = F(n - 1) + F(n - 2)
         *  初始化 F(0) = 0 F(1) = 1
         *  推演
         * @param n
         * @return
         */
        public int fib(int n) {
            if (n <= 1) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
