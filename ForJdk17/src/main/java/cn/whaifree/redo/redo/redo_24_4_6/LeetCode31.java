package cn.whaifree.redo.redo.redo_24_4_6;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 13:06
 * @注释
 */
public class LeetCode31 {
    @Test
    public void test() {
        int[] nums = {2,3,1};
        new Solution().nextPermutation(nums);
    }

    class Solution {
        public void nextPermutation(int[] nums) {
            // 123465 下一个排列 123546
            // 从后往前找到第一个升序的[a,x1,x2,b]
            // 从b那部分找到第一个大于x1的c
            // 替换x1 和c
            // reverse x2--
            int i = nums.length - 2;
            while (i >= 0) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
                i--;
            }

            if (i == -1) {
                reverse(nums, 0, nums.length - 1);
                return;
            }
            int j = nums.length - 1;
            while (j > i) {
                if (nums[j] > nums[i]) {
                    break;
                }
                j--;
            }

            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
        }

        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
