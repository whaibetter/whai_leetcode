package cn.whaifree.redo.redo_24_3_16;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 18:27
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        int jump = new Solution().jump(nums);
        System.out.println(jump);
    }

    class Solution {
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int jump = 0;
            int maxCover = 0;
            int curCover = 0; // 用于标记是否达到一次跳的终点

            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(maxCover, nums[i] + i);
                if (i == curCover) {
                    jump++;
                    curCover = maxCover;
                }

                if (curCover >= nums.length - 1) {
                    return jump;
                }
            }

            return jump;
        }
    }
}
