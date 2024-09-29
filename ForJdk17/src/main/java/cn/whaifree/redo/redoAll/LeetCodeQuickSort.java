package cn.whaifree.redo.redoAll;

import java.util.Arrays;
import java.util.Random;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/21 22:30
 * @注释
 */
public class LeetCodeQuickSort {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LeetCodeQuickSort().new Solution().sortArray(new int[]{110, 100, 0})));
    }

    class Solution {
        public int[] sortArray(int[] nums) {
            sort(nums, 0, nums.length - 1);
            return nums;
        }
        public static void sort(int[] nums, int leftIndex, int rightIndex) {
            if (leftIndex >= rightIndex) {
                return;
            }
            // ! 随机挑选一个幸运儿
            int q = new Random().nextInt(rightIndex - leftIndex + 1) + leftIndex;
            swap(nums, leftIndex, q);

            int pivot = nums[leftIndex];
            int l = leftIndex;
            int r = rightIndex;
            while (l < r) {
                while (l < r && nums[r] >= pivot) {
                    r--;
                }
                while (l < r && nums[l] <= pivot) {
                    l++;
                }
                swap(nums, l, r);
            }
            swap(nums, l, leftIndex);
            sort(nums, leftIndex, l - 1);
            sort(nums, r + 1, rightIndex);
        }

        public static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }




}
