package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 16:23
 * @注释
 */
public class LeetCode80 {

    @Test
    public void test() {
        int[] nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(new Solution().removeDuplicates(nums));
    }

    class Solution {
        /**
         * 双指针
         * - 跳跃判断，如果不一样就让left向前，并赋值
         *
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {
            if (nums.length < 3) return nums.length;
            int leftSlow = 0;
            int rightFast = 2;
            while (rightFast < nums.length) {
                if (nums[rightFast] != nums[leftSlow]) {
                    nums[leftSlow + 2] = nums[rightFast];
                    leftSlow++;
                }
                rightFast++;
            }
            return leftSlow + 2;
        }
    }


    class Solution1 {
        /**
         *
         *
         * @param nums
         * @return
         */
        public int removeDuplicates(int[] nums) {
            // 数组中的一号和二号元素肯定不用删除
            int index = 2;
            for(int i = 2 ; i < nums.length ; i++) {
                if(nums[i] != nums[index-2]) {
                    nums[index++] = nums[i];
                }
            }
            return index;
        }
    }
}
