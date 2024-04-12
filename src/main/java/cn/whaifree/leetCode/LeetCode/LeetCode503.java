package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/11 13:07
 * @注释
 */
public class LeetCode503 {

    @Test
    public void test()
    {
        int[] nums = {1,2,3,4,3};
        Solution solution = new Solution();
        int[] res = solution.nextGreaterElements(nums);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    class Solution {
        /**
         * 循环
         * 1. 两倍拼接
         * 2. 模拟走两遍
         * @param nums
         * @return
         */
        public int[] nextGreaterElements(int[] nums) {

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);
            for (int i = 1; i < nums.length * 2; i++) {
                if (nums[stack.peek()] < nums[i % nums.length]) {
                    // 找到了栈顶比他大的值
                    while (!stack.isEmpty() && nums[stack.peek()] < nums[i % nums.length]) {
                        res[stack.peek()] = nums[i % nums.length];
                        stack.pop();
                    }
                    stack.push(i % nums.length);
                } else if (nums[stack.peek()] > nums[i % nums.length]) {
                    stack.push(i % nums.length);
                } else {
                    stack.push(i % nums.length);
                }
            }
            return res;
        }

        public int[] nextGreaterElements1(int[] nums) {

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);
            for (int i = 1; i < nums.length * 2; i++) {
                // 找到了栈顶比他大的值
                int h = i % nums.length;
                while (!stack.isEmpty() && nums[stack.peek()] < nums[h]) {
                    res[stack.peek()] = nums[h];
                    stack.pop();
                }
                stack.push(h);
            }
            return res;
        }
    }
}
