package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/23 10:54
 * @注释
 */
public class LeetCode75 {

    @Test
    public void test() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        Solution solution = new Solution();
        solution.sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    class Solution {
        public void sortColors(int[] nums) {
            int index0 = 0;
            int index1 = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, index0);
                    if (nums[i] == 1) { // 有可能index0是1，然后没有这一段就会被换到最后去哦
                        swap(nums, i, index1);
                    }
                    index0++;
                    index1++;
                } else if (nums[i] == 1) {
                    swap(nums, i, index1);
                    index1++;
                } else {

                }
            }
        }

        public void swap(int[] nums,int i,int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
