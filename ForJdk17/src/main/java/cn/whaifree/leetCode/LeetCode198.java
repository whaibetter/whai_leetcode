package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/3 13:50
 * @注释
 */
public class LeetCode198 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = new int[]{2,7};
        int rob = solution.rob(nums);
        System.out.println(rob);
    }

    class Solution {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            // dp[i] 表示 从0-i偷窃都最大价值
            // dp[i] = max(dp[i-1],dp[i-2]+nums[i]])
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
            return dp[nums.length - 1];
        }
    }


}
