package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/14 12:24
 * @注释
 */
public class LeetCode416 {


    @Test
    public void test() {
        int[] nums = {1,2,3,5};
        boolean canPartition = new Solution3().canPartition(nums);
        System.out.println(canPartition);
    }

//    class Solution {
//        /**
//         * 分割为2部分
//         * @param nums
//         * @return
//         */
//        public boolean canPartition(int[] nums) {
//            Arrays.sort(nums);
//
//            int rightSum = 0;
//            for (int num : nums) {
//                rightSum += num;
//            }
//            int leftSum = 0;
//            for (int i = 0; i < nums.length; i++) {
//                if (leftSum == rightSum) {
//                    return true;
//                } else if (leftSum > rightSum) {
//                    return false;
//                }
//                leftSum += nums[i];
//                rightSum -= nums[i];
//            }
//            return false;
//        }
//    }

    class Solution {

        int sumHalf = 0;
        int nowSum = 0;
        /**
         * 回溯
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            for (int num : nums) {
                sumHalf += num;
            }
            sumHalf /= 2;
            return backTracking(nums, 0);
        }

        public boolean backTracking(int[] nums, int start) {
            if (nowSum == sumHalf) {
                return true;
            }else if (nowSum>sumHalf){
                return false;
            }

            boolean b = false;
            for (int i = start; i < nums.length; i++) {
                nowSum += nums[i];
                b = backTracking(nums, i + 1);
                // 不行，有可能某些元素没在集合内
                nowSum -= nums[i];
                if (b==true) return true;
            }

            return b;
        }
    }


    class Solution2 {
        /**
         * 转换为背包问题
         * nums 为商品的重量、同时为商品的价值
         * 背包容量为sum/2 且需要装满
         *
         *   0  1  2  3  4  5
         * 0 0  n0 n0 n0 n0 n0
         * 1 0
         * 2 0
         * 3 0
         * 4 0
         * 5 0
         *
         * 不放东西的最大价值 dp[i][j] = dp[i-1][j]
         * 放东西的最大价值 max(dp[i-1][j] , dp[i-1][j-nums[i]]+values[i])
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            int sumHalf = 0;
            for (int num : nums) {
                sumHalf += num;
            }
            if (sumHalf % 2 != 0) {
                return false;
            }
            sumHalf /= 2;

            int length = nums.length;
            int[][] dp = new int[length][sumHalf + 1];
//            for (int i = 0; i < length; i++) {
//                dp[i][0] = 0;
//            }
            for (int i = nums[0]; i <= sumHalf; i++) {
                dp[0][i] = nums[0];
            }

            for (int i = 1; i < length; i++) {
                for (int j = 1; j <= sumHalf; j++) {
                    if (nums[i] > j) {
                        // 放不下
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                    }

                    if (dp[i][j] == sumHalf) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class Solution3 {
        /**
         *  将nums的元素放入背包中
         *  1. 背包容量 sum/2
         *  2. 物品i重量nums[i]；价值nums[i]
         *
         *    0 1 2 3 4 5
         *  0
         *  1
         *  2
         *
         *  dp[j]表示 j背包容量的最大价值
         *
         *
         *
         * // 递推公式
         * 2. 放下 dp[j] = max（dp[j], dp[j-weight[i]]+nums[i]）
         *
         * 从后往前循环，每次取得状态不会和之前取得状态重合，这样每种物品就只取一次了。
         *
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }

            if (sum % 2 != 0) {
                return false;
            }

            // 包裹最大容量
            int packageCapacity = sum / 2;

            int[] dp = new int[packageCapacity + 1];

            for (int i = 0; i < nums.length; i++) {
                // 包的容量必须大于物品i重量才能放进去
                /**
                 * 如果正序遍历
                 * dp[1] = dp[1 - weight[0]] + value[0] = 15
                 *         此时容量1的背包已经放入了
                 * dp[2] = dp[2 - weight[0]] + value[0] = 30
                 *         此时又放入了一次
                 */
                for (int j = packageCapacity; j >= nums[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                    //剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target，優化時間複雜度（26ms -> 20ms）
                    if(dp[packageCapacity] == packageCapacity)
                        return true;
                }
            }

            // 包裹容量和目标值一样
            return dp[packageCapacity] == packageCapacity;
        }
    }

}
