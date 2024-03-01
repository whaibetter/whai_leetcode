package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 10:37
 * @注释
 */
public class LeetCode376 {

    @Test
    public void test() {
        System.out.println(new Solution().wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
    }

    class Solution {
        /**
         * 摆动序列
         *
         * @param nums
         * @return
         */
        public int wiggleMaxLength(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }

            // 计算两数差值
            int curSub = 0;
            int preSub = 0;

            int res = 1;

            for (int i = 1; i < nums.length; i++) {
                curSub = nums[i] - nums[i - 1];
                // preSub = 0 主要让第一次进入循环
                if ((curSub < 0 && preSub >= 0) || (curSub > 0 && preSub <= 0)) {
                    res++;
                    preSub = curSub;
                }
            }

            return res;

        }
    }

}
