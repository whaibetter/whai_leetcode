package cn.whaifree.leetCode.Array;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode215 {

    @Test
    public void main()
    {
        int i = 0;
        while (true) {
            i++;
        }
//
//        int[] nums = {3,2,1,5,6,4};
//        int k = 2;
//        Solution solution = new Solution();
//        int i = solution.findKthLargest(nums, k);
//        System.out.println(i);
    }

    class Solution {
        public int findKthLargest(int[] nums, int k) {


            // 小顶堆
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
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

            return priorityQueue.poll();
        }
    }

    @Test
    public void test()
    {
        int[] nums = {3,2,1,5,6,4};
        sort(nums);
    }

    /**
     * 所有非叶子节点x（自 n/2 开始，表示下面都是叶子节点）找到子节点中的最大值，如果比x 还大，swap。再排序下一个非叶子节点
     */
    public void sort(int[] nums) {
        sort(nums, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public void sort(int[] nums, int end) {
        if (end < 0) {
            return;
        }

        int nonLeaf = (end) / 2;
        while (nonLeaf >= 0) {

            TreeNode.constructTreeByArrayWithInteger(nums).printTree();
            int left = 2 * nonLeaf + 1;
            int right = 2 * nonLeaf + 2;

            if (left <= end &&right <= end) {
                if (nums[left] < nums[right]) {
                    if (nums[right] > nums[nonLeaf]) {
                        swap(nums, nonLeaf, right);
                    }
                }else {
                    if (nums[left] > nums[nonLeaf]) {
                        swap(nums, nonLeaf, left);
                    }
                }
            } else if (left <= end) {
                if (nums[left] < nums[right]) {
                    if (nums[right] > nums[nonLeaf]) {
                        swap(nums, nonLeaf, right);
                    }
                }
            }else if (right <= end){

            }

            nonLeaf--;
        }
        swap(nums, 0, end);
        sort(nums, end - 1);
    }

    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
