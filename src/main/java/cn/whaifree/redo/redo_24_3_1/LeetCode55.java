package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 11:21
 * @注释
 */
public class LeetCode55 {
    @Test
    public void test() {
        System.out.println(new Solution().canJump(new int[]{2,3,1,1,4}));
    }

    class Solution {
        public boolean canJump(int[] nums) {
            if (nums.length == 1) {
                return true;
            }

            int maxCover = 0;
            for (int i = 0; i <= maxCover; i++) {
                maxCover = Math.max(maxCover, i + nums[i]);
                if (maxCover >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }

}
