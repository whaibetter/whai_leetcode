package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/28 11:53
 * @注释
 */
public class LeetCode279 {

    @Test
    public void test(){
        Solution solution = new Solution();
        int numSquares = solution.numSquares(12);
        System.out.println(numSquares);
    }


    class Solution {
        public int numSquares(int n) {

            // 完全平方数 任意取，使其满足n容量的背包的最少数量
            // 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
            // dp[j] 表示容量为j的背包 最少 需要的 完全平方数 的数量

            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MAX_VALUE);


            // 求组合数就是外层for循环遍历物品，内层for遍历背包
            // 求排列数就是外层for遍历背包，内层for循环遍历物品 ｛1，0｝ ｛0，1｝
            // 本题都可以
            dp[0] = 0;
//            for (int j = 0; j <= n; j++) {
//                for (int i = 1; i * i <= j; j++) {
//                    // i-j*j 表示 之前的完全平方数 + 这个挖
//                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
//                }
//            }

            for (int i = 1; i * i <= n; i++) {
                for (int j = i * i; j <= n; j++) {
                    // i-j*j 表示 之前的完全平方数 + 这个挖
                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
                    //dp[j] 可以由dp[j - i * i]推出， dp[j - i * i] + 1 便可以凑成dp[j]。
                    // 或者说， dp[j-i*i]表示j-i*i的最小需要平方数，加上我们给到的i*i这一个平方数，就是+1，即dp[j - i * i] + 1
                }
            }

            return dp[n];
        }
    }

}
