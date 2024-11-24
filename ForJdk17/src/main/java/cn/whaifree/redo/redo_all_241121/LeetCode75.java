package cn.whaifree.redo.redo_all_241121;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/21 22:44
 * @注释
 */
public class LeetCode75 {

    class Solution {
        public void sortColors(int[] nums) {
            int left = 0;
            int right = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, left);
                    if (nums[i] == 1) { // 有可能index0是1，然后没有这一段就会被换到最后去哦
                        swap(nums, i, right);
                    }
                    left++;
                    right++;
                }else if (nums[i] == 1) {
                    swap(nums, i, right);
                    right++;
                }
            }
        }
        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
