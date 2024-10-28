package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/23 16:13
 * @注释
 */
public class LeetCode713 {

    @Test
    public void test() {
        int[] nums = {1,2,3};
        int k = 0;
        int res = new Solution1().numSubarrayProductLessThanK(nums, k);
        System.out.println(res);
    }

    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {


            int res = 0;
            int pro = 1;
            for (int left = 0, right = 0; right < nums.length; right++) {
                pro *= nums[right];
                while (pro >= k) {
                    // 一旦 找到比k小的，左边就往前，并且pro得到最新的
                    pro /= nums[left++];
                }
                res += right - left + 1;

            }

            return res;

        }
    }

    class Solution1 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            if (k <= 1) return 0; //1 <= nums[i] <= 1000
            int res = 0;
            int pro = 1;

            int left = 0;
            int right = 0;
            while (right < nums.length) {
                pro *= nums[right];
                // 直到总乘积大于k，左边才左移
                while (pro >= k) {
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
