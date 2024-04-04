package cn.whaifree.leetCode.Dynamic;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/27 11:46
 * @注释
 */
public class LeetCode377 {

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        int target = 4;
        int res = new Solution1().combinationSum4(nums, target);
        System.out.println(res);
    }


    /**
     * 超时
     */
    class Solution {
        int sum = 0;
        int res = 0;
        public int combinationSum4(int[] nums, int target) {
            backTracking(nums, target);
            return res;
        }

        public void backTracking(int[] nums, int target) {
            if (sum == target) {
                res++;
                return;
            }
            if (sum > target) {
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                backTracking(nums, target);
                sum -= nums[i];
            }

        }

    }

    /**
     * dp 完全背包
     */
    class Solution1 {

        /**
         * 1 2 3随意取，使得总包围target
         * @param nums
         * @param target
         * @return
         */
        public int combinationSum4(int[] nums, int target) {


            // dp[j] 表示 从0-i-1任取，使得满足包容量为j的可能性
            /**
             *   0  1  2  3  4
             *  [1, 1, 0, 0, 0]
             *  [1, 1, 2, 0, 0]
             *  [1, 1, 2, 4, 0]
             *  [1, 1, 2, 4, 7]
             */
            int[] dp = new int[target + 1];
            dp[0] = 1; // 都不放入 情况有一种
            for (int j = 1; j < target + 1; j++) {
                for (int i = 0; i < nums.length; i++) {
                    if (j >= nums[i]) {
                        dp[j] = dp[j] + dp[j - nums[i]];
                        // dp[j] 不放
                        // dp[j - nums[i]] 放，放也有多种情况
                    }
                }
                /**
                 * 如果把遍历nums（物品）放在外循环，
                 * 遍历target的作为内循环的话，举一个例子：
                 * 计算dp[4]的时候，结果集只有 {1,3} 这样的集合，忽略了{3,1}
                 */
            }
            /**
             * 如果求 组合数 就是外层for循环遍历物品，内层for遍历背包。
             * 如果求 排列数 就是外层for遍历背包，内层for循环遍历物品。
             */
            return dp[target];
        }


    }





}
