package cn.whaifree.dataStructure.heap;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/1 1:47
 * @注释
 */
public class MyHeap {

    @Test
    public void test() {
        Heap heap = new Heap();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // 测试数据
        int[] testData = {5, 3, 8, 1, 2, 7, 4, 6};

        // 插入数据
        for (int val : testData) {
            heap.add(val);
            priorityQueue.add(val);
        }

        while (!heap.heap.isEmpty() && !priorityQueue.isEmpty()) {
            int customTop = heap.getMin();
            int pqTop = priorityQueue.peek();

            System.out.println("Custom Heap Top: " + customTop + ", PriorityQueue Top: " + pqTop);

            if (customTop != pqTop) {
                System.out.println("Heaps are inconsistent.");
                return;
            }

            heap.pop();
            priorityQueue.poll();
        }

    }

    class Heap {

        List<Integer> heap = null;

        public Heap() {
            heap = new ArrayList<>();
        }


        public void add(int val) {
            // 放到最后
            heap.add(heap.size(), val);
            // 向上调整
            adjustDown();
        }



        public void adjustUp() {
            int noLeaf = (heap.size() - 2) / 2;
            while (noLeaf >= 0) {
                int left = noLeaf * 2 + 1;
                int right = noLeaf * 2 + 2;
                int maxIn = noLeaf;
                if (left < heap.size() && heap.get(left) < heap.get(noLeaf)) {
                    maxIn = left;
                }
                if (right < heap.size() && heap.get(right) < heap.get(noLeaf)) {
                    maxIn = right;
                }
                swap(maxIn, noLeaf);
                noLeaf--;
                TreeNode.constructTree(heap.toArray(value -> new Integer[0]));
            }

        }

        public void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }



        public void pop() {
            swap(0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            adjustDown();
        }


        /**
         * 从头部向下调整
         */
        public void adjustDown() {
//            int half = heap.size() >>> 1;
//            int k = 0;
//            while (k < half) {
//                int left = k * 2 + 1;
//                int right = k * 2 + 2;
//                int minIn = k;
//                if (left < heap.size() && heap.get(left) < heap.get(k)) {
//                    minIn = left;
//                }
//                if (right < heap.size() && heap.get(right) < heap.get(k)) {
//                    minIn = right;
//                }
//                if (minIn != k) {
//                    swap(minIn, k);
//                } else {
//                    break;
//                }
//            }

        }

        public int getMin() {
            return heap.get(0);
        }
    }

}
