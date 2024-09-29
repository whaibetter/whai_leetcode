package cn.whaifree.redo.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 11:43
 * @注释
 */
public class LeetCode84 {

    @Test
    public void test()
    {
        int[] heights = {2,1,5,6,2,3};
        int i = new Solution1().largestRectangleArea(heights);
        System.out.println(i);
    }

    class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] he = new int[heights.length + 2];
            System.arraycopy(heights, 0, he, 1, heights.length);
            heights = he;

            Deque<Integer> stack = new LinkedList<>();
            // 找到凸点
            stack.push(0);
            int maxR = 0;
            for (int i = 1; i < heights.length; i++) {
                if (heights[i] < heights[stack.peek()]) {
                    while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                        // 找到下坡后，挨个弹出栈（这个出栈位置的就是高），判断新的面积会不会更大
                        int mid = stack.pop();
                        if (!stack.isEmpty()) {
                            int left = stack.peek();
                            int right = i;
                            int h = heights[mid];
                            maxR = Math.max(maxR, (right - left - 1) * h);
                        }
                    }

                }
                stack.push(i);

            }
            return maxR;

        }
    }

    class Solution1 {
        public int largestRectangleArea(int[] heights) {
            // 每个记录左边第一个小于本身的位置
            int[] left = new int[heights.length];
            int[] right = new int[heights.length];
            left[0] = -1;
            for (int i = 1; i < heights.length; i++) {
                int index = i - 1;
                while (index >= 0 && heights[index] >= heights[i]) {
                    index = left[index];
                }
                left[i] = index;
            }
            // 每个记录右边边第一个小于本身的位置
            right[heights.length - 1] = heights.length;
            for (int i = heights.length - 2; i >= 0; i--) {
                int index = i + 1;
                while (index <= heights.length - 1 && heights[index] >= heights[i]) {
                    index = right[index];
                }
                right[i] = index;
            }

            int maxD = 0;
            for (int i = 0; i < heights.length; i++) {
                int leftIndex = left[i];
                int rightIndex = right[i];
                maxD = Math.max(maxD, (rightIndex - leftIndex - 1) * heights[i]);
            }
            return maxD;
        }
    }
}
