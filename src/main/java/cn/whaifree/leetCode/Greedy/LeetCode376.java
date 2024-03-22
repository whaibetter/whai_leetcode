package cn.whaifree.leetCode.Greedy;


import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/24 13:23
 * @注释
 */
public class LeetCode376 {

    @Test
    public void test() {
//        System.out.println(new Solution().wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println("========");
        System.out.println(new Solution().wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}));
    }


    class Solution {
        public int wiggleMaxLength(int[] nums) {
            if (nums.length <= 1) {
                return nums.length;
            }
            //当前差值
            int curDiff = 0;
            //上一个差值
            int preDiff = 0;
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                //得到当前差值
                curDiff = nums[i] - nums[i - 1];
                //如果当前差值和上一个差值为一正一负
                //等于0的情况表示初始时的preDiff
                if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                    count++;
                    preDiff = curDiff;
                }
            }
            return count;
        }
    }
}
