package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/29 12:54
 * @注释
 */
public class LeetCode453 {
    @Test
    public void test()
    {
        int[] nums = {1, 1,1};
        int res = new Solution1().minMoves(nums);
        System.out.println(res);
    }

    class Solution {
        /**
         * 让n-1个元素加1 等价于让1个元素-1，直到全部元素相等
         * @param nums
         * @return
         */
        public int minMoves(int[] nums) {
            int min = Integer.MAX_VALUE;
            for (int num : nums) {
                min = Math.min(min, num);
            }
            int res = 0;
            for (int num : nums) {
                res += Math.abs(min - num);
            }
            return res;
        }
    }

    class Solution1 {
        /**
         * 让n-1个元素加1 等价于让1个元素-1，直到全部元素相等
         *  前缀和解法
         * @param nums
         * @return
         */
        public int minMoves(int[] nums) {

           // 获取前缀和
            int length = nums.length;
            int[] preSum = new int[length + 1];
            // 找到最小的元素的index
            int minIndex = 0;
            for (int i = 1; i <= length; i++) {
                if (nums[minIndex] > nums[i - 1]) {
                    minIndex = i - 1;
                }
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

            return preSum[length] - nums[minIndex] * length;
        }

    }
}
