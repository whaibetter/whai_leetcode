package cn.whaifree.redo.redo.redo_24_1_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/1/13 13:58
 * @注释
 */
public class LeetCode239_false {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] ints = solution.maxSlidingWindow(nums, k);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {


            Deque<Integer> queue = new LinkedList<Integer>();
            int length = nums.length;
            int[] res = new int[length - k + 1];
            // 从0-k-1遍历，找到最大值
            // 存下标到res数组中

            for (int i = 0; i < k; i++) {

                while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                    queue.removeLast();
                }
                queue.addLast(i);
            }

            // 遍历剩余的k-1 --- length-1
            // 如果找到比当前优先队列首位大的下标，就加入队列
            // 并且需要判断当前窗口是不是在正确的区间内

            // 第一个元素必然是queue.peek对应的数值
            res[0] = nums[queue.peek()];
            for (int i = k; i < length; i++) {
                while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                    queue.removeLast();
                }
                queue.addLast(i);

                if (queue.peek() <= i - k) {
                    queue.removeFirst();
                }
                // 注入res
                Integer peek = queue.peek();
                res[i - k + 1] = nums[peek];

            }

            return res;

        }
    }
}
