package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 1:09
 * @注释
 */
public class LeetCode31 {

    @Test
    public void test() {
        // 123546321
        // 找到递增，在右边找到能够让其变大的最靠右的（此时右边部分一定递减），替换，逆转右半部分
        int[] nums = {1, 2, 3, 5, 4, 6,5,2,1};
        new Solution().nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num);
        }
    }

    class Solution {
        public void nextPermutation(int[] nums) {
            // 123465 下一个排列 123546321
            // 从后往前找到第一个升序的[a,x1,x2,b]
            // 从b那部分找到第一个大于x1的c
            // 替换x1 和c
            // reverse x2--
            int i;
            for (i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
            if (i == -1) {
                reverse(nums, 0, nums.length - 1);
                return;
            }
            int j;
            for (j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    break;
                }
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
