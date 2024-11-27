package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/3 13:35
 * @注释
 */
public class LeetCode215 {
    @Test
    public void test() {
        int[] nums = {1};
        int k = 1;
        int result = new Solution().findKthLargest(nums, k);
        System.out.println(result);
    }

    class Solution {
        /**
         * 快排
         * @param nums
         * @param k
         * @return
         */
        public int findKthLargest(int[] nums, int k) {
            return find(nums, 0, nums.length - 1, k);
        }

        public int find(int[] nums, int start, int end, int k) {
            if (start > end) {
                return -1;
            }
            int q = new Random().nextInt(end - start + 1) + start;
            swap(nums, q, end);

            int base = nums[end];
            int left = start;
            int right = end;
            while (start < end) {
                while (start < end && nums[start] <= base) {
                    start++;
                }
                while (start < end && nums[end] >= base) {
                    end--;
                }
                swap(nums, start, end);
            }
            swap(nums, start, right);
            if (start == nums.length - k) {
                return nums[start];
            } else if (start > nums.length - k) {
                // 左找
                return find(nums, left, start - 1, k);
            } else {
                // 右找
                return find(nums, start + 1, right, k);
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
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        int result = new Solution1().findKthLargest(nums, k);
        System.out.println(result);
    }

    class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>( new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            for (int num : nums) {
                priorityQueue.offer(num);
                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
            return priorityQueue.peek();
        }

    }


    class Solution2 {

        /**
         * 构建大根堆
         *
         *  构建每个小子树都是一个大根堆，再以子堆的父节点往上换
         *
         *
         *
         * 构建大根堆
         * 把根移动到最后
         * 移动k次，顶部就是了
         */
        static class Heap{
            int[] heapArray = null;
            public Heap(int[] heapArray) {
                this.heapArray = heapArray;
                buildMaxHeap(heapArray.length);
            }

            public void buildMaxHeap(int rightEdge) {
                // 遍历所有非叶子，让他们做下沉
                for (int i = rightEdge / 2 - 1; i >= 0; i--) {
                    build(i, rightEdge);
                }
            }

            /**
             *
             * @param index 从index左右节点向上查找
             * @param rightEdge 堆的有边界,开区间
             */
            public void build(int index, int rightEdge) {
                int leftSon = index * 2 + 1;
                int rightSon = index * 2 + 2;
                int large = index;
                if (leftSon < rightEdge && heapArray[large] < heapArray[leftSon]) {
                    large = leftSon;
                }
                if (rightSon < rightEdge && heapArray[large] < heapArray[rightSon]) {
                    large = rightSon;
                }

                if (index != large) {
                    swap(index, large);
                    build(large, rightEdge); // large换完后large已经是小的了，小的下沉到合适的位置
                    // 大的不断换上去，直到不换就退出
                }
            }


            public void swap(int i, int j) {
                int temp = heapArray[i];
                heapArray[i] = heapArray[j];
                heapArray[j] = temp;
            }

        }



        public int findKthLargest(int[] nums, int k) {
            Heap heap = new Heap(nums);
            int right = nums.length;
            // 构建出了最大堆
            for (int j = nums.length - 1; j >= nums.length - k + 1; j--) {
                heap.swap(0, j);
                heap.build(0, --right);
            }
            return heap.heapArray[0];
        }

    }

    @Test
    public void test21() {
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution2 solution = new Solution2();
        int i = solution.findKthLargest(nums, k);
        System.out.println(i);
    }
}
