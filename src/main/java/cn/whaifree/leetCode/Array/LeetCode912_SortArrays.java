package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 *
 * 排序算法有这几种：
 * 常见的排序算法包括但不限于以下这些：
 * 冒泡排序：从第一个元素开始与右侧元素两两比较并交换，直到右侧成为有序部分。
 * 选择排序：有序部分在左侧，在剩余元素中找到最小的那个元素，并与剩余元素中第一个元素交换。
 * 插入排序：有序部分在左侧，将剩余元素中第一个元素不断向左交换，直到此元素处于有序部分恰当位置。
 * 希尔排序：取一个间隔值，距离为间隔值的元素为一组，将整个数组分为若干组。每组内进行插入排序。缩小间隔值并重复，直到间隔值为1，即所有元素在同一组。
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/20 14:20
 * @注释
 */
public class LeetCode912_SortArrays {

    @Test
    public void test() {

        int[] nums = {5, 2, 3, 1,647,24,7,2,8,2,8,1,54,13,6,234,45,234,64,745,32,56,44,32,38};
        int[] res = new Solution2().sortArray(nums);
        System.out.println(Arrays.toString(res));

        int[] ints = Arrays.copyOf(res, res.length);
        Arrays.sort(nums);
        System.out.println(Arrays.equals(ints, nums));

    }


    class Solution {
        /**
         * 冒泡
         * @param nums
         * @return
         */
        public int[] sortArray(int[] nums) {
            boolean isSwap = false;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 1; j < nums.length - i; j++) {
                    if (nums[j] < nums[j - 1]) {
                        isSwap = true;
                        int tmp = nums[j];
                        nums[j] = nums[j - 1];
                        nums[j - 1] = tmp;
                    }

                }
                if (!isSwap) {
                    return nums;
                }
                isSwap = false;
            }
            return nums;
        }
    }

    class Solution1 {
        /**
         * 选择排序
         * 选择右边区间的最小值与左边替换
         * @param nums
         * @return
         */
        public int[] sortArray(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                int minIndex = i;
                for (int j = i+1; j < nums.length; j++) {
                    if (nums[minIndex] > nums[j]) {
                        minIndex = j;
                    }
                }
                // 交换min与i
                int tmp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = tmp;
            }
            return nums;
        }
    }

    class Solution2 {
        /**
         * 插入排序 j不断往前插入替换到合适位置
         * @param nums
         * @return
         */
        public int[] sortArray(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                int j = i;
                while (j > 0 && nums[j] < nums[j - 1]) {
                    // 交换j和j-1
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                    j--;
                }
            }
            return nums;
        }
    }

    class SolutionQuickSort {
        /**
         * 插入排序 j不断往前插入替换到合适位置
         *
         *
         * @param nums
         * @return
         */
        public int[] sortArray(int[] nums) {


            return nums;
        }

        public void part(int[] nums, int start, int end) {
            if (start > end) {
                return;
            }





        }

    }
}
