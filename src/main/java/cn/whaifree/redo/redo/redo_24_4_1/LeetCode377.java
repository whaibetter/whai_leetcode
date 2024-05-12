package cn.whaifree.redo.redo.redo_24_4_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 17:00
 * @注释
 */
public class LeetCode377 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = {9};
        int target = 3;
        int i = solution.combinationSum4(nums, target);
        System.out.println(i);
    }

    class Solution {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];

            dp[0] = 1;

            for (int j = 0; j < target + 1; j++) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= j) {
                        dp[j] = dp[j] + dp[j - nums[i]];
                    }
                }
            }

//            for (int i = 0; i < nums.length; i++) {
//                for (int j = nums[i]; j <= target; j++) {
//                    dp[j] = dp[j] + dp[j - nums[i]];
//                }
//            }

            return dp[target];
        }
    }
}
