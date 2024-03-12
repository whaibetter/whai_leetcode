package cn.whaifree.redo.redo_24_3_9;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 12:12
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 1, 4};
        int jump = new Solution().jump(nums);
        System.out.println(jump);
    }

    class Solution {
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            // 在最大覆盖范围内向前
            int maxCover = 0;
            int curCover = 0;
            int jumpCount = 0;
            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(maxCover, i + nums[i]);
                if (i == curCover) {
                    jumpCount++;
                    curCover = maxCover;
                    // 必须等待区间增加后再判断是否到末位
                    if (maxCover >= nums.length - 1) {
                        break;
                    }
                }
            }
            return jumpCount;


        }
    }
}
