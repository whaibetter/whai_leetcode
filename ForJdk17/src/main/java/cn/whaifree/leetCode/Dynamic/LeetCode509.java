package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/8 11:44
 * @注释
 */
public class LeetCode509 {

    @Test
    public void test() {
        System.out.println(new Solution1().fib(10));
    }

    class Solution {
        public int fib(int n) {
            if (n==0) return 0;
            if (n==1) return 1;
            return fib(n - 1) + fib(n - 2);
        }
    }


    class Solution1 {
        /**
         * 1. 确定dp数组（dp table）以及下标的含义
         * 2. 确定递推公式  dp[i] = dp[i - 1] + dp[i - 2];
         * 3. dp数组如何初始化 dp[0] = 0; dp[1] = 1;
         * 4. 确定遍历顺序
         * 5. 举例推导dp数组 0 1 1 2 3 5 8 13 21 34 55
         * @param n
         * @return
         */
        public int fib(int n) {
            if (n<=1) return n;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}
