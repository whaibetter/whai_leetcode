package cn.whaifree.redo.redo_24_3_9;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 19:38
 * @注释
 */
public class LeetCode376 {

    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length == 1) {
                return 1;
            }

            // 使用curSub作为当前差值
            // 使用preSub作为上一个和curSub相反地差值, 在不断增加的过程中，presub依然记录的是减去的过程
            int curSub = 0;
            int preSub = 0;
            int res = 0;

            for (int i = 1; i < nums.length; i++) {
                curSub = nums[i] - nums[i - 1];
                if ((curSub < 0 && preSub >= 0) || (curSub > 0 && preSub <= 0)) {
                    preSub = curSub;
                    res++;
                }
            }
            return res;

        }
    }
}
