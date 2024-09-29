package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/4 19:03
 * @注释
 */
public class LeetCode55 {

    @Test
    public void test()
    {
        int[] nums = {2,3,1,1,4};
        System.out.println(new Solution().canJump(nums));

        System.out.println(new Solution().canJump(new int[]{3, 2, 1, 0, 5}));
        System.out.println(new Solution().canJump(new int[]{1,2,3}));

    }

    class Solution {
        /**
         *
         *
         * @param nums
         * @return
         */
        public boolean canJump(int[] nums) {
            if (nums.length == 1) {
                return true;
            }

            int right = nums[0];
            int left = 0;
            while (left <= right) {
                if (left + nums[left] > right) {
                    right = left + nums[left];
                }
                if (right >= nums.length - 1) {
                    return true;
                }
                left++;
            }
            return false;
        }
    }
}
