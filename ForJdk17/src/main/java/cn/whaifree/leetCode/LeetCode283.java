package cn.whaifree.leetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 14:24
 * @注释
 */
public class LeetCode283 {

    @Test
    public void test() {
        int[] nums = {0, 4, 0, 3, 12, 0};
        new Solution().moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    class Solution {
        public void moveZeroes(int[] nums) {


            for (int i = 1; i < nums.length; i++) {
                // 插入排序
                for (int j = i; j > 0; j--) {
                    if (nums[j - 1] == 0) {
                        swap(nums, j, j - 1);
                    } else if (nums[j] != 0 && nums[j - 1] == 0) { // 遇到非0就往前插入
                        swap(nums, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

    @Test
    public void test1() {
        int[] nums = {0, 4, 0, 3, 12, 0};
        new Solution1().moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    class Solution1 {
        public void moveZeroes(int[] nums) {
            int left = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[left] = nums[i];
                    left++;
                }
            }
            for (int i = left; i < nums.length; i++) {
                nums[i] = 0;
            }
        }
    }
}
