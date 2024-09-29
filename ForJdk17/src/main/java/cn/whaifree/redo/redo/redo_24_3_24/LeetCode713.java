package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 14:33
 * @注释
 */
public class LeetCode713 {

    @Test
    public void test()
    {
        int[] nums = {10,5,2,6};
        int k = 100;
        int res = new Solution().numSubarrayProductLessThanK(nums,k);
        System.out.println(res);
    }

    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k == 0) {
                return 0;
            }

            int left = 0;
            int right = 0;
            int pro = 1;
            int res = 0;
            while (right < nums.length) {
                pro *= nums[right];
                while (pro >= k && left <= right) { // 1，1，1 1的时候，但也可以修改 if (k <= 1) return 0; 因为 1 <= nums[i] <= 1000
                    pro /= nums[left];
                    left++;
                }
                res += right - left + 1;
                right++;
            }

            return res;
        }
    }
}
