package cn.whaifree.redo.redo_24_4_1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 16:07
 * @注释
 */
public class LeetCode279 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int i = solution.numSquares3(1);
        System.out.println(i);
    }

    class Solution {
        public int numSquares(int n) {

            // dp[j] 表示从[0-i*i]和为j的完全平方数的数量
            // dp[j] = dp[j-i*i]+1
            int[] dp = new int[n+1];
            int sqrt = (int) Math.sqrt(n);
            Arrays.fill(dp, Integer.MAX_VALUE);


            dp[0] = 0;
            for (int j = 1; j < n + 1; j++) {
                for (int i = 1; i <= sqrt; i++) {
                    if (j >= i * i) {
                        dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                    }
                }
            }
            return dp[n];
        }

        public int numSquares1(int n) {

            // dp[j] 表示从[0-i*i]和为j的完全平方数的数量
            // dp[j] = dp[j-i*i]+1
            int[] dp = new int[n+1];
            Arrays.fill(dp, Integer.MAX_VALUE);


            dp[0] = 0;
            for (int j = 1; j < n + 1; j++) {
                for (int i = 1; i * i <= j; i++) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
            return dp[n];
        }

        public int numSquares3(int n) {

            // dp[j] 表示从[0-i*i]和为j的完全平方数的数量
            // dp[j] = dp[j-i*i]+1
            int[] dp = new int[n+1];
            Arrays.fill(dp, Integer.MAX_VALUE);


            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = i * i; j <= n; j++) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
            return dp[n];
        }
    }
}
