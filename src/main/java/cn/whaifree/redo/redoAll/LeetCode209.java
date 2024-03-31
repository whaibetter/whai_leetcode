package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 14:53
 * @注释
 */
public class LeetCode209 {

    @Test
    public void test() {
        int[] nums = {1,2,3,4,5};
        int target = 11;
        int minSubArrayLen = new Solution().minSubArrayLen(target, nums);
        System.out.println(minSubArrayLen);
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            int nowSum = 0;
            int left = 0;
            int right = 0;
            int minLength = Integer.MAX_VALUE;

            while (right < nums.length) {
                nowSum += nums[right];
                while (nowSum >= target) {
                    minLength = Math.min(right - left + 1, minLength);
                    nowSum -= nums[left];
                    left++;
                }
                right++;
            }

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }
    }
}
