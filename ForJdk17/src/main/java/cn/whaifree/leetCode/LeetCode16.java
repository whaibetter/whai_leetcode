package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/28 14:50
 * @注释
 */
public class LeetCode16 {

    @Test
    public void test() {
        int[] nums = {4, 0, 5, -5, 3, 3, 0, -4, -5};
        System.out.println(new Solution().threeSumClosest(nums, -2));

    }

    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int closeV = Integer.MAX_VALUE;
            for (int left = 0; left < nums.length; left++) {
                int mid = left + 1;
                int right = nums.length - 1;
                while (mid < right) {
                    int mv = nums[mid] + nums[left] + nums[right];

                    if (Math.abs(mv - target) < Math.abs(closeV - target)) {
                        closeV = mv;
                    }

                    if (mv > target) {
                        right = right - 1;
                    } else if (mv < target) {
                        mid = mid + 1;
                    } else {
                        return target;
                    }
                }
            }

            return closeV;
        }
    }


}
