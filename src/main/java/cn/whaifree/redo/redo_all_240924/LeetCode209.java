package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 10:41
 * @注释
 */
public class LeetCode209 {

    @Test
    public void test() {
        int[] nums = {1,4,4};
        int target = 4;
        int i = new Solution().minSubArrayLen(target, nums);
        System.out.println(i);
    }


    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int right = 0;
            int left = 0;
            int nowSum = 0;
            int res = Integer.MAX_VALUE;
            while (right < nums.length) {
                nowSum += nums[right];
                while (left <= right && nowSum >= target) {
                    res = Math.min(res, right - left + 1);
                    nowSum -= nums[left];
                    left++;
                }
                right++;
            }
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 9, 10};
        int i = Arrays.binarySearch(ints, 6);
        System.out.println(i);
    }

    @Test
    public void test1() {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        int i = new Solution1().minSubArrayLen(target, nums);
        System.out.println(i);
    }

    class Solution1 {


        /**
         * | x  | target  |
         * 0    n         i
         * @param target
         * @param nums
         * @return
         */

        public int minSubArrayLen(int target, int[] nums) {

            int[] preSum = new int[nums.length];
            preSum[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i];
            }

            int res = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int A = preSum[i];
                if (A < target) {
                    continue;
                }
                int tar = A - target;
                int index = Arrays.binarySearch(preSum, tar);
                if (index < 0) {
                    index = -index - 1;
                } else {
                    index = index + 1;
                }
                res = Math.min(res, i - index + 1);
            }
            return res == Integer.MAX_VALUE ? 0 : res;
        }
    }
}
