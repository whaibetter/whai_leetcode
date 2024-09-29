package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 13:40
 * @注释
 */
public class LeetCode416 {

    @Test
    public void test() {
        int[] nums = {1,2,8,5};
        System.out.println(new Solution1().canPartition(nums));
    }


    class Solution {
        /**
         *
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            Arrays.sort(nums);

            int sum = Arrays.stream(nums).sum();
            if (sum%2==1){
                return false;
            }
            int target = sum / 2;

            // 背包容量 target
            // 物品 nums
            int[][] dp = new int[nums.length][target + 1];
            // dp[i][j] 表示从0-i任意选择，放入容量为j的背包的最大价值

            for (int i = nums[0]; i <= target; i++) {
                dp[0][i] = nums[0];
            }

            for (int i = 1; i < nums.length; i++) {
                for (int j = 1; j <= target; j++) {
                    if (j > nums[i]) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    }else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    if (dp[i][j] == target) {
                        return true;
                    }
                }
            }

            return dp[nums.length - 1][target] == target;
        }
    }

    class Solution1 {
        /**
         *
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
//            Arrays.sort(nums);

            int sum = Arrays.stream(nums).sum();
            if (sum % 2 == 1) {
                return false;
            }
            int target = sum / 2;

            // 背包容量 target
            // 物品 nums
            int[] dp = new int[target + 1];
            // dp[i][j] 表示从0-i任意选择，放入容量为j的背包的最大价值

            for (int i = nums[0]; i <= target; i++) {
                dp[i] = nums[0];
            }

            for (int i = 1; i < nums.length; i++) {
                for (int j = target; j > 0; j--) {
                    if (j > nums[i]) {
                        dp[j] = Math.max(dp[j - nums[i]] + nums[i], dp[j]);
                    }
                    if (dp[j] == target) {
                        return true;
                    }
                }
            }

            return dp[target] == target;
        }
    }


}
