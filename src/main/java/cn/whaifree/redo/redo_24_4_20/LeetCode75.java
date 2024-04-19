package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/17 11:38
 * @注释
 */
public class LeetCode75 {

    @Test
    public void test() {
        int[] nums = {1, 2, 0};
        new Solution1().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
    class Solution {
        /**
         * 0 1 2三个元素，第一次遍历全部把0放前面
         * @param nums
         */
        public void sortColors(int[] nums) {
            int indexZero = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, i, indexZero++);
                }
            }

            for (int i = indexZero; i < nums.length; i++) {
                if (nums[i] == 1) {
                    swap(nums, i, indexZero++);
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution1 {
        /**
         * 双指针：两个指针指向0和1应该swap的地方
         * @param nums
         */
        public void sortColors(int[] nums) {

            int indexZero = 0;
            int indexOne = 0;

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    swap(nums, indexZero, i);
                    // 此时有可能index0指向的是1，这样交换会把1交换到后面去
                    // 即交换后的i指向的可能为1
                    // 需要把这个1给他替换到index1对应的位置
                    if (indexZero < indexOne) {
                        swap(nums, i, indexOne);
                    }
                    indexOne++;
                    indexZero++;
                }else if (nums[i] == 1){
                    swap(nums, indexOne++, i);
                }
            }
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution2 {
        /**
         * 双指针：将所有0变到头部，所有2变到尾部
         * @param nums
         */
        public void sortColors(int[] nums) {

            int index0 = 0;
            int index2 = nums.length - 1;
            for (int i = 0; i <= index2; i++) {
                while (index2 >= i && nums[i] == 2) {
                    swap(nums, index2--, i);
                }
                if (nums[i] == 0) {
                    swap(nums, index0++, i);
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
