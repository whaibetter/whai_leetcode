package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/10 18:40
 * @注释
 */
public class LeetCode96 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int i = solution.numTrees(3);
        System.out.println(i);
    }

    class Solution {
        /**
         * 1 1
         * 2 2
         * 节点
         *
         * n=4
         *
         *  左边三个节点，右边没有 dp[3] + dp[0]
         *                     dp[2] + dp[1]
         *                     dp[1] + dp[2]
         *                     dp[0] + dp[3]
         *
         * @param n
         * @return
         */
        public int numTrees(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i < dp.length; i++) {
                int var = 0;
                for (int j = 0; j < i; j++) {
                    var += (dp[i - j - 1] * dp[j]);
                }
                dp[i] = var;
            }
            return dp[n];
        }
    }
}
