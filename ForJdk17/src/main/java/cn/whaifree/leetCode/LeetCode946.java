package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 19:38
 * @注释
 */
public class LeetCode946 {

    @Test
    public void test() {
        new Solution().validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
    }
    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            return success(pushed, popped);
        }

        public boolean success(int[] input, int[] output) {
            LinkedList<Integer> stack = new LinkedList<>();
            int index = 0;
            for (int i : input) {
                stack.push(i);
                while (!stack.isEmpty() && stack.peek() == output[index]) {
                    index++;
                    stack.pop();
                }
            }
            return stack.isEmpty();
        }
    }
}
