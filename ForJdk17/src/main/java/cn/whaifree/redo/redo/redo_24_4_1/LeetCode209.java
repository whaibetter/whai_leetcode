package cn.whaifree.redo.redo.redo_24_4_1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 15:07
 * @注释
 */
public class LeetCode209 {
    @Test
    public void test() {

        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int i = new Solution2().minSubArrayLen(target, nums);
        System.out.println(i);
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int right = 0;
            int left = 0;
            int sum = 0;
            int minLength = Integer.MAX_VALUE;
            while (right < nums.length) {
                sum += nums[right];
                while (left <= right && sum >= target) {
                    minLength = Math.min(minLength, right - left + 1);
                    sum -= nums[left];
                    left++;
                }
                right++;
            }
            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }
    }

    class Solution2 {
        /**
         * <img src="http://so9vd9h8j.hd-bkt.clouddn.com/image-20240329122057505.png"></img>
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen(int target, int[] nums) {

            // 前缀和
            int[] preSum = new int[nums.length + 1];
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }


            int minLen = Integer.MAX_VALUE;
            for (int i = 0; i < preSum.length; i++) {
                // preSum[right] = target + preSum[i]
                int fill = target + preSum[i];
                int right = Arrays.binarySearch(preSum, fill);
                if (right < 0) {
                    right = -right - 1;
                }
                if (right < preSum.length) {
                    minLen = Math.min(minLen, right - i);
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }
}
