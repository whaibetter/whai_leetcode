package cn.whaifree.redo.redo.redo_24_3_16;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/17 16:09
 * @注释
 */
public class LeetCode376 {

    @Test
    public void test() {
        int[] nums = {0,0,0};
        int res = new Solution().wiggleMaxLength(nums);
        System.out.println(res);
    }

    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length < 2) {
                return 1;
            }

            // 记录前一个的正负
            // 后一个如果和前一个是相反的 res++ 并且相反值变换
            //          不是相反的 不处理
            int pre = 0;
            int cur = 0;

            int res = 1;
            for (int i = 1; i < nums.length; i++) {
                cur = nums[i] - nums[i - 1];
                // pre=0 确保第一次能够进去
                if ((cur > 0 && pre <= 0) || (cur < 0 && pre >= 0)) {
                    res++;
                    pre = cur;
                }
            }
            return res;
        }
    }
}
