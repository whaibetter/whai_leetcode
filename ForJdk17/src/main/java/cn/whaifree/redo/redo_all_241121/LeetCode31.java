package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/30 13:13
 * @注释
 */
public class LeetCode31 {

    @Test
    public void test() {
        int[] nums = {1,2,3};
        new Solution().nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    /**
     * 1 2 3 8 2 4
     * 1 2 8 3 2 4
     *  此时 还需要找啊到第一个比i-1小的2换到3这里，再reverse
     * 1 2 8 2 3 4
     *
     */

    class Solution {
        public void nextPermutation(int[] nums) {
            int i;
            for (i = nums.length - 1; i > 0; i--) {
                if (nums[i] > nums[i - 1]) {
                    break;
                }
            }
            if (i == 0) {
                reverse(nums, 0, nums.length - 1);
                return;
            }

            // 从后面部分找到第一个大于i-1的
            for (int j = nums.length - 1; j > i - 1; j--) {
                if (nums[j] > nums[i - 1]) {
                    swap(nums, j, i - 1);
                    break;
                }
            }


            reverse(nums, i, nums.length - 1);
        }

        public void reverse(int[] nums, int left, int right) {
            while (left < right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
