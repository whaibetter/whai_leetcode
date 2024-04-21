package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

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

        int[] nums = {5,1,1,2,0,0};

//        int[] res = new Solution2().sortArray(nums);
//        System.out.println(Arrays.toString(res));
//
//        int[] ints = Arrays.copyOf(res, res.length);
//        Arrays.sort(nums);
//        System.out.println(Arrays.equals(ints, nums));

        int[] res2 = new MergeSort.Solution().sortArray(nums);

        System.out.println(Arrays.toString(res2));
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

class MergeSort{
    static class Solution {

        // 归并排序
        public int[] sortArray(int[] nums) {
            if (nums.length <= 1) {
                return nums;
            }
            int mid = nums.length / 2;
            int[] left = Arrays.copyOfRange(nums, 0, mid);
            int[] right = Arrays.copyOfRange(nums, mid, nums.length);
            left = sortArray(left);
            right = sortArray(right);
            return merge(left, right);
        }

        public int[] merge(int[] left, int[] right) {
            int[] res = new int[left.length + right.length];
            int leftIndex = 0;
            int rightIndex = 0;
            int index = 0;
            while (leftIndex < left.length && rightIndex < right.length) {
                if (left[leftIndex] < right[rightIndex]) {
                    res[index] = left[leftIndex];
                    leftIndex++;
                }else {
                    res[index] = right[rightIndex];
                    rightIndex++;
                }
                index++;
            }
            while (leftIndex < left.length) {
                res[index] = left[leftIndex];
                leftIndex++;
                index++;
            }
            while (rightIndex < right.length) {
                res[index] = right[rightIndex];
                rightIndex++;
                index++;
            }
            return res;
        }



    }
}

class QuickSort{

    /**
     * 快速排序
     */
    static class Solution4 {
        public int[] sortArray(int[] nums) {

            quickSort(nums, 0, nums.length-1);
            return nums;
        }

        public int[] quickSort(int[] nums, int left,int right){
            //如果左指针大于右指针，怎退出循环
            if(left > right){
                return null;
            }

            //! 随机挑选一个幸运儿
            int q = new Random().nextInt(right - left + 1) + left;
            swap(nums, right, q);

            int base = nums[left];
            int i = left;
            int j = right;
            while(i != j){
                //从右往左遍历，当右指针指向的元素大于等于基数时，j--。右指针持续向左移动
                while(nums[j]>=base && i < j){
                    j--;
                }
                //从左往右遍历，当左指针指向的元素小于等于基数时，i++。左指针持续向右移动
                while(nums[i]<=base && i < j){
                    i++;
                }
                //当左右两个指针停下来时，交换两个元素
                swap(nums, i, j);

            }
            swap(nums,i,left);
            quickSort(nums,left, i-1);
            quickSort(nums,i+1,right);
            return nums;
        }



        public void swap(int[] nums, int index1, int indexB) {
            int tmp = nums[index1];
            nums[index1] = nums[indexB];
            nums[indexB] = tmp;
        }
    }

    class Solution11 {
        public int[] sortArray(int[] nums) {
            quick_sort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quick_sort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }


            int i = left;
            int j = right;
            //! 随机挑选一个幸运儿
            int q = new Random().nextInt(right - left + 1) + left;
            swap(nums, right, q);

            while (i < j) {
                while (nums[i] <= nums[right] && i < j) {
                    i++;
                }
                while (nums[right] >= nums[left] && i < j) {
                    j--;
                }
                swap(nums, i, j);
            }

            swap(nums, i, right);


            quick_sort(nums, left, i - 1);
            quick_sort(nums, i + 1, right);
        }



        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
