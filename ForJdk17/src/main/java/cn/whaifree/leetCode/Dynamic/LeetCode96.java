package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/11 13:08
 * @注释
 */
public class LeetCode96 {

    @Test
    public void test() {
        int n = 5;
        int result = new Solution().numTrees(n);
        System.out.println(result);
    }

    class Solution {

        /**
         *
         * dp[i] 表示有两个节点的可能数
         *
         * 以n=3举例，二叉搜索树
         * 可能的方案：
         * i表示以i为头节点
         * 1. 以1为头结点 右边2个子树+左边0个子树
         * 2. 以2为头节点 右边1个子树+左边1个子树
         * 3. 以3为头节点 右边0个子树+左边2个子树
         *
         * dp[3] = 以1为头 dp[2] * dp[0] +
         *         以2为头 dp[1] * dp[1] +
         *         以3为头 dp[0] * dp[2]
         *
         * dp[i] = dp[i-1] * dp[0] +
         *         dp[i-2] * dp[1] +
         *         dp[i-3] * dp[02] +
         *         ....
         *         dp[0] * dp[i-1]
         * @param n
         * @return
         */
        public int numTrees(int n) {

            if (n <= 1) {
                return 1;
            }

            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += (dp[i - j] * dp[j - 1]);
                }
            }

            return dp[n];
        }
    }
}
