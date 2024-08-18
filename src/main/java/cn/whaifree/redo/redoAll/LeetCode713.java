package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/18 23:36
 * @注释
 */
public class LeetCode713 {

    @Test
    public void test(){
        // 10,9,10,4,3,8,3,3,6,2,10,10,9,3
        int[] nums = {10,9,10,4,3,8,3,3,6,2,10,10,9,3};
        int k = 19;
        System.out.println(new Solution1().numSubarrayProductLessThanK(nums, k));
    }

    class Solution {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int nowSum = 1;
                for (int j = i; j < nums.length; j++) {
                    nowSum *= nums[j];
                    if (nowSum < k) {
                        res++;
                    }else {
                        break;
                    }
                }
            }
            return res;
        }
    }


    class Solution1 {
        public int numSubarrayProductLessThanK(int[] nums, int k) {
            int res = 0;
            int left = 0;
            int right = 0;
            int tmpSum = 1;
            while (right < nums.length) {
                tmpSum *= nums[right];
                while (tmpSum >= k && left <= right) {
                    tmpSum /= nums[left];
                    left++;
                }
                right++;
                res += right - left;
            }
            return res;
        }
    }
}
