package cn.whaifree.redo.redo_24_3_16;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 16:40
 * @注释
 */
public class LeetCode96 {

    @Test
    public void test() {
        int n = 4;
        int result = new Solution().numTrees(n);
        System.out.println(result);
    }


    class Solution {
        public int numTrees(int n) {
            if (n == 1) {
                return 1;
            }
            //dp[i]表示i个节点的可能的树的数量
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            dp[2] = 2;

            for (int i = 3; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    dp[i] += dp[i - j] * dp[j - 1];
                }
            }

            return dp[n];
        }
    }
}
