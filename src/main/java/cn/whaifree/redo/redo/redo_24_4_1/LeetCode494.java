package cn.whaifree.redo.redo.redo_24_4_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 17:34
 * @注释
 */
public class LeetCode494 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int i = solution.findTargetSumWays(nums, target);
        System.out.println(i);
    }

    class Solution {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (Math.abs(target) > sum) return 0; // 此时没有方案
            if ((target + sum) % 2 == 1) return 0; // 此时没有方案

            int left = (sum + target) / 2;

            // dp[j] 表示 从0-i 中任意取，背包容量为 j 时，能装下的组合数
            // dp[j] = dp[j] + dp[j-nums[i]]
            int[] dp = new int[left + 1];
            dp[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = left; j >= nums[i]; j--) {
                    // 已经有一个1（nums[i]） 的话，有 dp[4]种方法 凑成 容量为5的背包。
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
            return dp[left];
        }
    }
}
