package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/20 23:30
 * @注释
 */
public class LeetCode279 {

    @Test
    public void test() {
        int n = 13;
        int i = new Solution().numSquares(n);
        System.out.println(i);
    }

    class Solution {
        /**
         * dp[j] 表示容量为j的背包需要几个完全平方数，可以填满
         *
         * @param n
         * @return
         */
        public int numSquares(int n) {
            int pakage = n + 1;
            int[] dp = new int[pakage];
            for (int i = 1; i <= n; i++) {
                dp[i] = i;
            }

            for (int i = 2; i <= n; i++) {
                for (int j = i * i; j <= n; j++) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }

            return dp[n];
        }
    }

    class Solution1 {
        /**
         * dp[j] 表示容量为j的背包需要几个完全平方数，可以填满
         *
         * @param n
         * @return
         */
        public int numSquares(int n) {
            int pakage = n + 1;
            int x = (int) Math.floor(Math.sqrt(n));
            int[][] dp = new int[x + 1][pakage];
            for (int i = 0; i <= n; i++) {
                dp[1][i] = Integer.MAX_VALUE;
            }

            for (int i = 1; i <= x; i++) {
                for (int j = i; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= i * i)
                        dp[i][j] = Math.min(dp[i][j - i * i] + 1, dp[i][j]);
                }
            }

            return dp[x][n];
        }
    }
}
