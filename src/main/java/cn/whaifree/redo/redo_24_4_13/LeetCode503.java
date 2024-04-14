package cn.whaifree.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 13:29
 * @注释
 */
public class LeetCode503 {
    @Test
    public void test()
    {
        int[] nums = new int[]{1,2,3,4,3};
        Solution solution = new Solution();
        int[] res = solution.nextGreaterElements(nums);
        for (int i : res) {
            System.out.println(i);
        }
    }

    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int[] res = new int[nums.length];
            Arrays.fill(res, -1);

            int len = nums.length;
            int length = len * 2;
            for (int i = 1; i < length; i++) {
                while (!stack.isEmpty() && nums[i % len] > nums[stack.peek()]) {
                    res[stack.pop()] = nums[i % len];
                }
                stack.push(i % len);
            }
            return res;
        }
    }
}
