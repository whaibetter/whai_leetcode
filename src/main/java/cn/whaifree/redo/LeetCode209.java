package cn.whaifree.redo;

import org.junit.Test;

public class LeetCode209 {

    @Test
    public void test() {
        System.out.println(new Solution().minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {


            int left = 0;
            int right = 0;
            int min = Integer.MAX_VALUE;
            int sum = 0;

            while (right < nums.length) {
                sum += nums[right];

                while (sum >= target){
                    // sum内删除left
                    sum -= nums[left];
                    min = Math.min(min, right - left + 1);
                    left++;
                }
                right++;
            }



            return min == Integer.MAX_VALUE ? 0 : min;
        }


    }
}
