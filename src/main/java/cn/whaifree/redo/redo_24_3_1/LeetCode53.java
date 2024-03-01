package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 11:01
 * @注释
 */
public class LeetCode53 {

    @Test
    public void test(
    ) {
        System.out.println(new Solution().maxSubArray(new int[]{-2,-1}));
    }

    class Solution {
        /**
         * 一旦加的那个区间为负，只会拖累后面的串，从下一个重加
         */
        public int maxSubArray(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            int maxSum = Integer.MIN_VALUE;
            int curSum = 0;
            for (int i = 0; i < nums.length; i++) {
                curSum += nums[i];
                maxSum = Math.max(curSum, maxSum);
                if (curSum < 0) {
                    curSum = 0;
                }
            }
            return maxSum;
        }
    }
}
