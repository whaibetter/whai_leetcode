package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 15:01
 * @注释
 */
public class LeetCode912 {

    @Test
    public void test() {
        int[] nums = {5,2,3,1};
        new QuickSort().sort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    class QuickSort {

        Random random = new Random();
        public int[] sortArray(int[] nums) {
            sort(nums, 0, nums.length - 1);
            return nums;
        }

        public void sort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }

            int index = start + random.nextInt(end - start + 1);
            swap(nums, index, end);
            int base = nums[end];


            int l = start;
            int r = end;
            while (start < end) {
                while (start < end && nums[start] <= base) {
                    start++;
                }
                while (start < end && nums[end] >= base) {
                    end--;
                }
//                if (start < end) {
                    swap(nums, start, end);
//                }
            }
            swap(nums, start, r);
            sort(nums, l, end - 1);
            sort(nums, start + 1, r);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    @Test
    public void test2() {
        int[] nums = {5,2,3,1};
        System.out.println(Arrays.toString(new MergeSort().sortArray(nums)));
    }

    class MergeSort {
        public int[] sortArray(int[] nums) {
            if (nums.length <= 1) {
                return nums;
            }
            if (nums.length == 2) {
                if (nums[0] > nums[1]) {
                    return new int[]{nums[1], nums[0]};
                }else {
                    return new int[]{nums[0], nums[1]};
                }
            }

            int len = nums.length ;
            int[] ints1 = Arrays.copyOfRange(nums, 0, len / 2);
            int[] ints2 = Arrays.copyOfRange(nums, len / 2, len);
            int[] sort1 = sortArray(ints1);
            int[] sort2 = sortArray(ints2);
            int[] sort = sort(sort1, sort2);
            return sort;
        }

        public int[] sort(int[] nums1, int[] nums2) {



            int index1 = 0;
            int index2 = 0;
            int index = 0;
            int[] res = new int[nums1.length + nums2.length];
            while (index1 < nums1.length && index2 < nums2.length) {
                if (nums1[index1] <= nums2[index2]) {
                    res[index++] = nums1[index1++];
                } else {
                    res[index++] = nums2[index2++];
                }
            }
            while (index1 < nums1.length) {
                res[index++] = nums1[index1++];
            }
            while (index2 < nums2.length) {
                res[index++] = nums2[index2++];
            }
            return res;
        }
    }

    @Test
    public void test3() {
        int[] nums = {-1,2,-8,-10};
        System.out.println(Arrays.toString(new HeapSort().sortArray(nums)));
    }

    class HeapSort {


        public int[] sortArray(int[] nums) {
            Heap heap = new Heap(nums);
            for (int i = nums.length - 1; i > 0; i--) {
                heap.heapUp(i);
            }
            return nums;
        }

        static class Heap{
            int[] heap = null;

            public Heap(int[] nums) {
                heap = nums;
            }
            public void heapUp(int end) {
                int nonLeaf = (end - 1) / 2;
                while (nonLeaf >= 0) {
                    int left = nonLeaf * 2 + 1;
                    int right = nonLeaf * 2 + 2;
                    int maxIndex = nonLeaf;
                    if (left <= end && heap[left] > heap[maxIndex]) {
                        maxIndex = left;
                    }
                    if (right <= end && heap[right] > heap[maxIndex]) {
                        maxIndex = right;
                    }
                    if (nonLeaf != maxIndex) {
                        swap(this.heap, nonLeaf, maxIndex);

                    }
                    nonLeaf--;
                }
                swap(heap, 0, end);
            }
            public void swap(int[] nums, int i, int j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }
}
