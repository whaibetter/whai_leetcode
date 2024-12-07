package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 15:47
 * @注释
 */
public class LeetCode209 {

    @Test
    public void test() {
        int target = 4;
        int[] nums = {1, 4, 4};
        Solution solution = new Solution();
        int res = solution.minSubArrayLen(target, nums);
        System.out.println(res);
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int right = 0;
            int left = 0;
            int res = Integer.MAX_VALUE;
            int nowSum = 0;
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
}
