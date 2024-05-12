package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 16:14
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test()
    {
        Solution solution = new Solution();
        int[] nums = {2,3,1,1,4};
        int jump = solution.jump(nums);
        System.out.println(jump);
    }

    class Solution {
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int maxCover = 0;
            int curCover = 0; // 当前区间

            int jump = 0;
            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(maxCover, i + nums[i]);
                // 当遍历到达当前覆盖范围的边界时，更新当前覆盖范围的起始位置，并增加一次跳跃次数
                if (i == curCover) {
                    curCover = maxCover;
                    jump++;
                }
                // 如果当前覆盖范围已经到达或超过数组最后一个位置，则返回跳跃次数
                if (curCover >= nums.length - 1) {
                    return jump;
                }
            }
            return jump;

        }
    }
}
