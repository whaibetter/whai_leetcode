package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/22 11:51
 * @注释
 */
public class LeetCode84 {

    @Test
    public void test()
    {
        int[] heights = {2,1,2};
        int i = new Solution1().largestRectangleArea(heights);
        System.out.println(i);
    }

    class Solution {
        public int largestRectangleArea(int[] heights) {
            if (heights.length == 1) {
                return heights[0];
            }
            /**
             * 找到凸点
             * 对于每个index，找到左右第一个比他矮的
             */

            int length = heights.length;
            int[] left = new int[length];
            int[] right = new int[length];
            left[0] = -1;

            int leftIndex = 0; // 标记第一个小于当前i的
            for (int i = 1; i < length; i++) {
                leftIndex = i - 1;
                while (leftIndex >= 0 && heights[leftIndex] >= heights[i]) {
                    // 也可以简单粗暴的leftIndex--,但会超时
                    leftIndex = left[leftIndex];
                }
                left[i] = leftIndex;
            }

            right[length - 1] = length;
            int rightIndex = 0; // 标记第一个小于当前i的
            for (int i = length - 2; i >= 0; i--) {
                rightIndex = i + 1;
                while (rightIndex <= length - 1 && heights[rightIndex] >= heights[i]) {
                    rightIndex = right[rightIndex];
                }
                right[i] = rightIndex;
            }

            int maxRegin = 0;
            for (int i = 0; i < length; i++) {
                maxRegin = Math.max(heights[i] * (right[i] - left[i] - 1), maxRegin);
            }
            return maxRegin;
        }
    }

    class Solution1 {
        public int largestRectangleArea(int[] heights) {

            int[] ints = new int[heights.length + 2];
            System.arraycopy(heights, 0, ints, 1, heights.length);
            heights = ints;

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int maxRegin = 0;
            for (int i = 1; i < heights.length; i++) {
                if (heights[i] < heights[stack.peek()]) {
                    while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                        Integer pop = stack.pop();
                        if (!stack.isEmpty()) {
                            maxRegin = Math.max(maxRegin, (i - stack.peek() - 1) * heights[pop]);
                        }
                    }
                }
                stack.push(i);
            }
            return maxRegin;
        }
    }
}
