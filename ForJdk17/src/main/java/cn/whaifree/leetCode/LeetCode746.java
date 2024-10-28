package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/8 12:24
 * @注释
 */
public class LeetCode746 {

    @Test
    public void test() {
        System.out.println(new Solution().minCostClimbingStairs(new int[]{1,1001}));
    }

    class Solution {

        /**
         * 含义 dp[i] 走到这最少支出的花费
         * 递推公式
         *      dp[i] = min{dp[i-2]+cost[i-2] , dp[i-1]+cost[i-1]}
         * 初始化 dp[0]=0 dp[1]=0
         * 模拟推导
         * 1,100,1,1,1,100,1,1,100,1
         * 0  0  1 2
         *
         * @param cost
         * @return
         */
        public int minCostClimbingStairs(int[] cost) {
            //一旦你支付此费用，即可选择向上爬一个或者两个台阶。
            int length = cost.length;
            int[] dp = new int[length+1];
            dp[0] = 0;
            dp[1] = 0;

            for (int i = 2; i <= length; i++) {
                // 前面两个数有可能跳到本数，判断前两个数跳本数的代价
                dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
            }
            return dp[length];
        }
    }
}
