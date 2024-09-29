package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 13:22
 * @注释
 */
public class LeetCode96 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int numTrees = solution.numTrees(5);
        System.out.println(numTrees);
    }

    class Solution {
        /**
         * dp[0] = 1;
         * dp[1] = 1;
         * dp[2] = 2;
         * dp[3] =
         *      左边0 * 右边2
         *      左边1 * 右边1
         *      左边2 * 右边0
         *
         * dp[k] =
         *      i=0-k-1
         *      左边i * 右边k-i-1
         *      左边i+1 * 右边k-i-2
         *      ...
         *      左边0 * 右边k-1
         *
         * @param n
         * @return
         */
        public int numTrees(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int k = 3; k < dp.length; k++) {
                int sum = 0;
                for (int i = 0; i < k; i++) {
                    sum += dp[i] * dp[k - i - 1];
                }
                dp[k] = sum;
            }
            return dp[n];
        }
    }
}
