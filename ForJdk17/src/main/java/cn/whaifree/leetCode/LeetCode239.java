package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author whai文海
 * @Date 2024/1/9 14:49
 * @注释
 *
 * 239. 滑动窗口最大值

 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 示例 2：
 *
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class LeetCode239 {

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = new Solution().maxSlidingWindow(nums, 3);
        for (int re : res) {
            System.out.println(re);
        }
    }

    class Solution {
        /**
         * 维持一个单调队列，队列里存放的是对应值得下标。
         * 如果 此次队列头部指针为i，即num[i]为目前窗口的最大值。
         *  - 如果这个最大值的下标不在下一个窗口的范围内，那么需要将这个值删除
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            // 单调队列，维持一个单调递减的队列
            Deque<Integer> queue = new LinkedList<>();

            // n个元素，k个窗口，一共为n-k个输出
            int[] res = new int[nums.length - k + 1];


            for (int i = 0; i < nums.length; i++) {

                // 判断当前队首的值是否在本次的窗口内，i-k为窗口左边
                if (!queue.isEmpty()&&queue.peek() <= i - k) {
                    queue.pop();
                }

                // 保证单调递减
                while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                    queue.removeLast();
                }
                queue.addLast(i);


                // 从第k个开始才有结果
                if(i+1 >= k){
                    res[i+1-k] = nums[queue.peek()];
                }

                // 从第k个开始，先把值存进去，在下次循环判断是否这个值存在于这个新的区间中
            }


            return res;

        }
    }
}
