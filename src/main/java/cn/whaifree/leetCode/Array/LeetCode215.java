package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class LeetCode215 {


    @Test
    public void sort_EmptyArray_ShouldHandleGracefull3y() {
        int[] nums = {3,2,1,5,6,4};
        System.out.println(new Solution5().findKthLargest(nums, 2));
        System.out.println(Arrays.toString(nums));

    }

    class Solution5 {

        /**
         * 快速排序，某次base放对位置后，左边刚刚好有k-1个，就找到了
         * @param nums
         * @param k
         * @return
         */
        public int findKthLargest(int[] nums, int k) {
            return sort(nums, 0, nums.length - 1, k);
        }

        /**
         * 快速排序思路，对前n-1个进行不断交换，最后把基准替换到交接点
         * @param nums
         * @param start
         * @param end
         * @param k
         * @return
         */
        public int sort(int[] nums, int start, int end, int k) {

            if (start > end) {
                return nums[end];
            }

            int q = new Random().nextInt(end - start + 1) + start;

            swap(nums, q, end);

            int base = nums[end];
            int left = start;
            int right = end;
            while (left < right) {

                //从左往右遍历，当左指针指向的元素小于等于基数时，i++。左指针持续向右移动
                while (nums[left] >= base && left < right) {
                    left++;
                }
                //从右往左遍历，当右指针指向的元素大于等于基数时，j--。右指针持续向左移动
                while (nums[right] <= base && left < right) {
                    right--;
                }
                if (left < right) {
                    //当左右两个指针停下来时，交换两个元素
                    swap(nums, left, right);
                }
            }
            swap(nums, left, end);

            // 从大到小排序，如果左边k-1个，则left就是第k个，左边k-1个比他大
            if (left == k - 1) {
                return nums[left];
            }
            // 左边的数量太少了，往右边找
            if (left < k - 1) {
                return sort(nums, left + 1, end, k);
            }
            return sort(nums, start, left - 1, k);

        }
        public void swap(int[] heap, int start, int end) {
            int temp = heap[start];
            heap[start] = heap[end];
            heap[end] = temp;
        }
    }


    @Test
    public void sort_EmptyArray_ShouldHandleGracefully8() {

        // [3,2,3,1,2,4,5,5,6], k = 4
        int[] nums = {5,4,3,2,1};
        System.out.println(new Solution4().findKthLargest(nums, 2));
    }

    class Solution4 {
        public int findKthLargest(int[] nums, int k) {
            for (int i = nums.length - 1; i > 0; i--) {
                shiftUp(nums, i);
            }
            System.out.println(Arrays.toString(nums));
            return nums[nums.length - k];
        }

        public void shiftUp(int[] heap, int end) {
            int parent = (end - 1) / 2;
            while (parent >= 0) {
                int left = parent * 2 + 1;
                int right = parent * 2 + 2;
                int k = parent;
                if (left <= end &&heap[left] > heap[k]) {
                    k = left;
                }
                if (right <= end && heap[right] > heap[k]) {
                    k = right;
                }
                swap(heap, parent, k);
                parent--;
            }
            swap(heap, 0, end);
        }

        public void swap(int[] heap, int start, int end) {
            int temp = heap[start];
            heap[start] = heap[end];
            heap[end] = temp;
        }
    }

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

//    @Test
//    public void test188()
//    {
//        new sol().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 3);
//
//
//    }

//    class sol{
//        /**
//         *    3
//         *   / \
//         *  /   \
//         *  5   -2147483648
//         * /
//         * 2       右边那个有问题，所以不行
//         * @param nums
//         * @param k
//         * @return
//         */
//        public int findKthLargest(int[] nums, int k) {
//            Heap heap = new Heap(k);
//            for (int num : nums) {
//                heap.add(num);
//            }
//            return 1;
//        }
//    }


//    class Heap{
//
//
//        int[] heap = null;
//
//        public Heap(int k) {
//            this.heap = new int[k + 1];
//            Arrays.fill(this.heap, Integer.MIN_VALUE);
//        }
//
//        public void add(int num) {
//            heap[heap.length - 1] = num;
//            shiftUp(heap, heap.length - 1);
//        }
//
//
//        /**
//         * 固定长度的，让其 shiftUp
//         * @param nums
//         * @param numIndex 将 numsIndex 位置上移
//         */
//        public void shiftUp(int[] nums, int numIndex) {
//            int k = numIndex;
//            while (k > 0) {
//                int parent = (k - 1) / 2;
////                if (nums[numIndex] < nums[parent]) { // 小顶堆
//                if (nums[k] > nums[parent]) { // 大顶堆
//                    // 小顶堆，小的上移
//                    swap(nums, parent, k);
//                    k = parent;
//                }else {
//                    break;
//                }
//                TreeNode.constructTreeByArrayWithInteger(nums);
//            }
//
//        }
//
//        public void swap(int[] nums, int start, int end) {
//            int temp = nums[start];
//            nums[start] = nums[end];
//            nums[end] = temp;
//        }
//
//    }

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
    public void test1()
    {

        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        Solution1 solution = new Solution1();
        int i = solution.findKthLargest(nums, k);
        System.out.println(i);
    }

    class Solution1 {
        public int findKthLargest(int[] nums, int k) {
            sort(nums, nums.length - 1);

            return nums[k - 1];
        }

        /**
         * 堆排序思路：
         * 1. 依次遍历非叶节点   nonLeaf = (end - 1) / 2; --
         *      选取左右两边比他大的替换上来，不断替换直到最上面是最大的
         * 2. 把最大的堆顶移动到最后，确定一个最大值
         * @param nums
         * @param end
         */
        public void sort(int[] nums, int end) {
            if (end <= 0) {
                return;
            }
            int heapSize = nums.length;
            for (int i = heapSize / 2; i >= 0; --i) {
                int l = i * 2 + 1, r = i * 2 + 2, largest = i;
                if (l < heapSize && nums[l] > nums[largest]) {
                    largest = l;
                }
                if (r < heapSize && nums[r] > nums[largest]) {
                    largest = r;
                }
                if (largest != i) {
                    swap(nums, i, largest);
                    sort(nums, heapSize - 1);
                }
            }
            swap(nums, 0, end);
        }

        public void swap(int[] nums, int start, int end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }


    }




    @Test
    public void sort_EmptyArray_ShouldHandleGracefully() {
        int[] nums = {};
        sort(nums);
        assertArrayEquals(new int[0], nums);
    }

    @Test
    public void sort_NaturalNumberArray_ShouldSortInAscendingOrder() {
        int[] nums = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        sort(nums);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9}, nums);
    }

    @Test
    public void sort_IntegerArray_ShouldSortInAscendingOrder() {
        int[] nums = {10, -1, 2, 5, 0, 6, -3, 4};
        sort(nums);
        assertArrayEquals(new int[]{-3, -1, 0, 2, 4, 5, 6, 10}, nums);
    }

    @Test
    public void sort_SingleElementArray_ShouldRemainUnchanged() {
        int[] nums = {5};
        sort(nums);
        assertArrayEquals(new int[]{5}, nums);
    }

    @Test
    public void sort_DuplicateElementsArray_ShouldSortInAscendingOrder() {
        int[] nums = {4, 2, 2, 8, 3, 3, 1};
        sort(nums);
        assertArrayEquals(new int[]{1, 2, 2, 3, 3, 4, 8}, nums);
    }

    @Test
    public void sort_NegativeNumberArray_ShouldSortInAscendingOrder() {
        int[] nums = {-1, -2, -3, -4, -5};
        System.out.println(Arrays.toString(new Solution3().sortArray(nums)));
    }

    class Solution3 {
        public int[] sortArray(int[] nums) {
            sort(nums, nums.length - 1);
            return nums;
        }

        /**
         * 堆排序思路：
         * 1. 依次遍历非叶节点   nonLeaf = (end - 1) / 2; --
         *      选取左右两边比他大的替换上来，不断替换直到最上面是最大的
         * 2. 把最大的堆顶移动到最后，确定一个最大值
         * @param nums
         * @param end
         */
        public void sort(int[] nums, int end) {
            if (end <= 0) {
                return;
            }

            int nonLeaf = (end - 1) / 2;
            while (nonLeaf >= 0) {


                int left = 2 * nonLeaf + 1;
                int right = 2 * nonLeaf + 2;


                int maxIn = nonLeaf; // 父子三个节点的最大值
                if (left <= end && nums[maxIn] < nums[left]) {
                    maxIn = left;
                }
                if (right <= end && nums[maxIn] < nums[right]) {
                    maxIn = right;
                }
                swap(nums, nonLeaf, maxIn);



//            // noleaf至少有一个子节点
//            if (left <= end &&right <= end) {
//                if (nums[left] < nums[right]) {
//                    if (nums[right] > nums[nonLeaf]) {
//                        swap(nums, nonLeaf, right);
//                    }
//                }else {
//                    if (nums[left] > nums[nonLeaf]) {
//                        swap(nums, nonLeaf, left);
//                    }
//                }
//            } else {
//                // 只有左边一个节点
//                if (nums[left] > nums[nonLeaf]) {
//                    swap(nums, nonLeaf, left);
//                }
//            }
                nonLeaf--;
            }
            swap(nums, 0, end );
            sort(nums, end - 1);
        }

        public void swap(int[] nums, int start, int end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
    }


    /**
     * 所有非叶子节点x（自 n/2 开始，表示下面都是叶子节点）找到子节点中的最大值，如果比x 还大，swap。再排序下一个非叶子节点
     */
    public void sort(int[] nums) {
        sort(nums, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 堆排序思路：
     * 1. 依次遍历非叶节点   nonLeaf = (end - 1) / 2; --
     *      选取左右两边比他大的替换上来，不断替换直到最上面是最大的
     * 2. 把最大的堆顶移动到最后，确定一个最大值
     * @param nums
     * @param end
     */
    public void sort(int[] nums, int end) {
        if (end <= 0) {
            return;
        }

        int nonLeaf = (end - 1) / 2;
        while (nonLeaf >= 0) {


            int left = 2 * nonLeaf + 1;
            int right = 2 * nonLeaf + 2;


            int maxIn = nonLeaf; // 父子三个节点的最大值
            if (left <= end && nums[maxIn] < nums[left]) {
                maxIn = left;
            }
            if (right <= end && nums[maxIn] < nums[right]) {
                maxIn = right;
            }
            swap(nums, nonLeaf, maxIn);



//            // noleaf至少有一个子节点
//            if (left <= end &&right <= end) {
//                if (nums[left] < nums[right]) {
//                    if (nums[right] > nums[nonLeaf]) {
//                        swap(nums, nonLeaf, right);
//                    }
//                }else {
//                    if (nums[left] > nums[nonLeaf]) {
//                        swap(nums, nonLeaf, left);
//                    }
//                }
//            } else {
//                // 只有左边一个节点
//                if (nums[left] > nums[nonLeaf]) {
//                    swap(nums, nonLeaf, left);
//                }
//            }
            nonLeaf--;
        }
        swap(nums, 0, end );
        sort(nums, end - 1);
    }

    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}
