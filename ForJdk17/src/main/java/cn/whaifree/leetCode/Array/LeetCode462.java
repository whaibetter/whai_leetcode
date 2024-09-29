package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/29 13:15
 * @注释
 */
public class LeetCode462 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {1,2,3};
        int i = solution.minMoves2(nums);
        System.out.println(i);
    }

    class Solution {
        /**
         * +1 或 -1
         * @param nums
         * @return
         */
        public int minMoves2(int[] nums) {
            // 排序后找到中位数
            Arrays.sort(nums);
            // 前缀和
            int length = nums.length;
            int[] preSum = new int[length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            // 中位数
            int middleIndex = length / 2;
            int middleValue = nums[middleIndex];

            int left = middleIndex * middleValue - preSum[middleIndex];
            int right = preSum[preSum.length - 1] - preSum[middleIndex] - (length - middleIndex) * middleValue;
            return left + right;
        }
    }


}
