package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 18:22
 * @注释
 */
public class LeetCode416 {


    @Test
    public void test() {
        int[] nums = {1,6,5};
        boolean canPartition = new Solution().canPartition(nums);
        System.out.println(canPartition);
    }

    class Solution {
        /**
         * 把n个数放入到背包中
         * dp[j] 为0-i中任取放入容量为j的背包的最大价值，让价值等于nums总数的一半
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % 2 == 1) {
                return false;
            }
            int half = sum / 2;

            int[] dp = new int[half + 1];


            /**
             * 内层循环从容量的一半开始，逐渐减小，直到小于当前物品的重量。
             */
            for (int i = 0; i < nums.length; i++) {
                for (int j = half; j >= nums[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);

                    if(dp[half] == half)  return true;
                }
            }



            return dp[half] == half;
        }
    }

    class Solution1 {
        /**
         * 把n个数放入到背包中
         * dp[i][j] 为0-i中任取放入容量为j的背包的最大价值，让价值等于nums总数的一半
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {


            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % 2 == 1) {
                return false;
            }
            int half = sum / 2;

            int[][] dp = new int[nums.length][half + 1];
            for (int i = nums[0]; i < half + 1; i++) {
                dp[0][i] = nums[0];
            }


            for (int i = 1; i < nums.length; i++) {
                for (int j = 1; j <= half; j++) {
                    if (j < nums[i]) {
                        dp[i][j] = dp[i - 1][j];
                    }else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    }
                }
            }

            return dp[nums.length - 1][half] == half;
        }
    }


}
