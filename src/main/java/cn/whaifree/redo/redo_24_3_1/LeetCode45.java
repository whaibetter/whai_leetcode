package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;


/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 11:36
 * @注释
 */
public class LeetCode45 {

    @Test
    public void test() {
        System.out.println(new Solution().jump(new int[]{2,3,1,1,4}));
    }

    class Solution {
        /**
         * 每个index都有一个覆盖区间，每个覆盖区间之间只jump一次
         * @param nums
         * @return
         */
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int maxCover = 0; // 最大覆盖区间
            int curCover = 0; // 当前覆盖区间
            int jumpCount = 0; // 跳跃数
            for (int i = 0; i < nums.length; i++) {
                maxCover = Math.max(maxCover, i + nums[i]);
                // 每次jump count++
                if (maxCover >= nums.length - 1) {
                    jumpCount++;
                    break;
                }
                if (i == curCover) {
                    jumpCount++;
                    curCover = maxCover; // 到了最后一个，[a,b] a已经执行完了，下次就是b区间中jump一次
                }

            }
            return jumpCount;
        }
    }

}
