package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/8 19:52
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        int[] nums = {2,3,1,1,4};
        int jump = new Solution().jump(nums);
        System.out.println(jump);
    }

    class Solution {
        /**
         *
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            int curCover = 0;
            int maxCover = 0;
            int jump = 0;
            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(maxCover, i + nums[i]);
                // 如果从这个 起跳点 起跳叫做第 1 次 跳跃，
                // 那么从后面 nums[0]个格子起跳 都 可以叫做第 2 次 跳跃。
                if (i == curCover) {
                    curCover = maxCover;
                    jump++;
                }
                if (curCover >= nums.length - 1) {
                    return jump;
                }
            }
            return jump;
        }
    }

    @Test
    public void test1() {
        int[] nums = {2,3,1,1,4};
        int jump = new Solution1().jump(nums);
        System.out.println(jump);
    }

    class Solution1 {
        /**
         * [2,3,1,1,4]
         *  0 1 1 2 2
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            // 动态规划
            int[] dp = new int[nums.length];
            dp[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (nums[j] >= i - j) {
                        min = Math.min(min, dp[j] + 1);
                    }
                }
                dp[i] = min;
            }
            return dp[nums.length - 1];
        }
    }

}
