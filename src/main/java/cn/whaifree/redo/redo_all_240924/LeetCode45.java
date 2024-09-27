package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 16:38
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new Solution().jump(nums));
    }

    class Solution {
        public int jump(int[] nums) {
            int curCover = 0;
            int maxCover = 0;
            int jump = 0;
            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(i + nums[i], maxCover);
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

    class Solution1 {
        public int jump(int[] nums) {
            int left = 0;
            int right = 0;
            int jump = 0;
            int minJump = Integer.MAX_VALUE;
            while (left < nums.length) {
                int tmpFar = left + nums[left];
                // 找到每一跳最远能覆盖到哪，贪心
                while (left <= right) {
                    tmpFar = Math.max(tmpFar, left + nums[right]);
                    left++;
                }
                jump++;
                if (tmpFar >= nums.length - 1) {
                    minJump = Math.min(minJump, jump);
                    break;
                }
                right = tmpFar;
            }
            return minJump == Integer.MAX_VALUE ? 1 : minJump;

        }
    }

}
