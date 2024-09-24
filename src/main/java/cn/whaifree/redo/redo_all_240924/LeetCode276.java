package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 13:51
 * @注释
 */
public class LeetCode276 {

    @Test
    public void test() {
        int[] nums = {0,0,0};
        int res = new Solution().wiggleMaxLength(nums);
        System.out.println(res);

    }
    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2) {
                return nums.length;
            }

            int preSum = 0;
            int nowSum = 0;

            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                nowSum = nums[i] - nums[i - 1];
                if ((nowSum > 0 && preSum <= 0) || (nowSum < 0 && preSum >= 0)) {
                    // 方向相反
                    count++;
                    preSum = nowSum;
                } else {
                    // 方向相同

                }
            }
            return count;
        }
    }
}
