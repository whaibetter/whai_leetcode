package cn.whaifree.redo.redo.redo_24_1_13;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/13 16:25
 * @注释
 */
public class LeetCode209 {

    @Test
    public void test() {
        System.out.println(new Solution().minSubArrayLen(41, new int[]{1,1,1,4,4}));
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {

            int left = 0;
            int right = 0;
            int minLength = Integer.MAX_VALUE;

            int sum = 0;
            do {
                if (sum + nums[right] >= target) {
                    if (right - left + 1 < minLength) {
                        minLength = right - left + 1;
                    }
                    sum -= nums[left];
                    left++;
                } else {
                    sum += nums[right];
                    right++;
                }

            } while (right < nums.length); // 让右指针移动就好了

            return minLength == Integer.MAX_VALUE ? 0 : minLength;
        }

    }
}
