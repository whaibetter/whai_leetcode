package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/24 12:30
 * @注释
 */
public class LeetCode215 {

    @Test
    public void test() {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(new Solution().findKthLargest(nums, k));
    }

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            heap = nums;
            // 构建大根堆，最上面的最大
            for (int i = heap.length / 2 - 1; i >= 0; i--) {
                build(i, heap.length - 1);
            }

            // 从非叶子节点不断向下递归


            // 把堆顶最大swap最后，最后再有新的边界从堆顶开始向下换
            for (int i = heap.length - 1; i > heap.length - k; i--) {
                swap(0, i);
                build(0, i - 1);
            }
            return heap[0];
        }

        int[] heap = null;

        public void build(int startToDown, int edge) {
            int left = startToDown * 2 + 1;
            int right = startToDown * 2 + 2;
            int maxIndex = startToDown;
            if (left <= edge && heap[maxIndex] < heap[left]) {
                maxIndex = left;
            }
            if (right <= edge && heap[maxIndex] < heap[right]) {
                maxIndex = right;
            }

            if (maxIndex != startToDown) {
                swap(startToDown, maxIndex);
                build(maxIndex, edge);
            }
        }

        public void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

}
