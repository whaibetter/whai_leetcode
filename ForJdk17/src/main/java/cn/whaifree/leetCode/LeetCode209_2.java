package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 *
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 *
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 *
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * 进阶：
 *
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class LeetCode209_2 {

    /**
     * 暴力求解，会超时
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
                /**
                 * if (sum >= target && j - i +1 < minLength) {
                 *                     minLength = j - i + 1;
                 *                     break;
                 *                 }
                 */
            }
        }


        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public int minSubArrayLen1(int target, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (right < nums.length ) {
            sum += nums[right];
            // 窗口内，找到最小子串
            while (sum >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left++];
            }
            right++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }



    class Solution {
        /**
         * 前缀和做法
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen(int target, int[] nums) {
            // 求前缀和
            int length = nums.length;
            int[] preSum = new int[length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            // 2,3,1,2,4,3
            // 因为每个元素都是正数，所以preSum是递增的，可以用二分查找
            int minLengthFillSolution = Integer.MAX_VALUE;
            for (int i = 1; i < preSum.length; i++) {
                // 从0开始查找
                int fill = target + preSum[i - 1];
                int intervalEnd = Arrays.binarySearch(preSum, fill); // 没找到就会返回负数
                if (intervalEnd < 0) {
                    intervalEnd = -intervalEnd - 1; // 防止查出来的是负数
                }
                // 这个区间如果合理，就可能是正常的区间
                if (intervalEnd <= length)
                    minLengthFillSolution = Math.min(minLengthFillSolution, intervalEnd - (i - 1));
                // 注意区分下标 intervalEnd和i-1 前缀和为fill和target
            }
            return minLengthFillSolution == Integer.MAX_VALUE ? 0 : minLengthFillSolution;
        }
    }

    @Test
    public void list() {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));

//        System.out.println(minSubArrayLen1(5, new int[]{2,3,6}));
    }

}
