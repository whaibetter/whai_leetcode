package cn.whaifree.redo.redo_24_3_16;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/17 17:04
 * @注释
 */
public class LeetCode416 {
    @Test
    public void test() {
        int[] nums = {1, 5, 11, 5};
        boolean canPartition = new Solution().canPartition(nums);
        System.out.println(canPartition);
    }

    class Solution {
        /**
         * 0-1背包问题
         *
         * dp[i][j] 表示 放入物品i后，容量为j的背包的最大价值
         *
         * 背包容量为sum/2
         * 物品重量为nums[i]
         * 物品价值为nums[i]
         *
         * [3,5,11,5]
         *
         *   0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 1
         * 0 0 0 0 3 3
         * 1
         * 2
         *
         *
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
            int half = sum / 2;
            int length = nums.length;
            int[][] dp = new int[length][half+1];

            for (int i = nums[0]; i <= half; i++) {
                dp[0][i] = nums[0];
            }

            // dp[i][j] 表示 放入物品i后，容量为j的背包的最大价值
            for (int i = 1; i < length; i++) {
                for (int j = 1; j <= half; j++) {
                    // 放的下
                    if (j >= nums[i]) {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                dp[i - 1][j - nums[i]] + nums[i]
                        );
                    }else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    // 放不下
                    if (dp[i][j] == half) {
                        return true;
                    }

                }
            }
            return false;

        }
    }

    class Solution1 {
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
