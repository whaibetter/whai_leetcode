package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 16:57
 * @注释
 */
public class LeetCode96 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int i = solution.numTrees(3);
        System.out.println(i);
    }

    class Solution {
        public int numTrees(int n) {
            if (n <= 2) {
                return n;
            }

            /**
             *
             * 二叉搜索树
             *
             * dp[i] 表示i颗树有的可能性
             * dp[0] = 1
             * dp[1] = 2
             *
             * n = 3
             * 以1为头 以2为头 以3为头
             * dp[0] * dp[2] +
             * dp[1] * dp[1] +
             * dp[2] * dp[0]
             *
             * n
             * dp[i] * dp[n-i-1]
             * ....
             * dp[n-i-1] * dp[i]
             */

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i < dp.length; i++) {
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += (dp[i - j - 1] * dp[j]);
                }
                dp[i] = sum;
            }

            return dp[n];
        }
    }
}
