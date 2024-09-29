package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/8 12:11
 * @注释
 */
public class LeetCode70 {

    @Test
    public void test() {
        System.out.println(new Solution().climbStairs(0));
    }

    class Solution {
        /**
         * 1. 确定dp数组含义  dp[i] 表示到这里的方法数
         * 2. 地推公式  dp[i] = dp[i-1]+dp[i-2]
         * 3. 初始化 dp[0] = 1;dp[1]=1
         * 4. 模拟推导1 1 2 3 5 8
         *           0 1 2 3 4 5
         * @param n
         * @return
         */
        public int climbStairs(int n) {
            if (n <= 1) {
                return n;
            }
            int[] dp = new int[n];
            dp[0] = 1;
            dp[1] = 2;
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n-1];
        }
    }

}
