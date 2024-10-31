package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 11:15
 * @注释
 */
public class LeetCode84 {

    @Test
    public void test() {
        int[] heights = {1,1};
        Solution solution = new Solution();
        int i = solution.largestRectangleArea(heights);
        System.out.println(i);
    }

    class Solution {
        /**
         * 15
         *
         *
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            int[] left = new int[heights.length];
            int[] right = new int[heights.length];

            // 对每个元素找到左边第一个比他小的
            left[0] = -1;
            for (int i = 0; i < heights.length; i++) {
                int index = i - 1;
                while (index >= 0 && heights[index] >= heights[i]) {
                    index = left[index];
                }
                left[i] = index;
            }
            // 对每个元素找到右边第一个比他小的
            right[heights.length - 1] = heights.length;
            for (int i = heights.length - 1; i >= 0; i--) {
                int index = i + 1;
                while (index <= heights.length - 1 && heights[index] >= heights[i]) {
                    index = right[index];
                }
                right[i] = index;
            }

            int maxArea = 0;
            for (int i = 0; i < right.length; i++) {
                maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
            }
            return maxArea;
        }
    }

    class Solution1 {
        /**
         * 29
         *
         *
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            int[] h = new int[heights.length + 2];
            System.arraycopy(heights, 0, h, 1, heights.length);
            heights = h;

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);

            int res = 0;
            for (int i = 1; i < heights.length; i++) {
                while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                    Integer pop = stack.pop();
                    if (!stack.isEmpty()) {
                        int right = i;
                        int left = pop;
                        int region = (right - left - 1) * heights[pop];
                        res = Math.max(res, region);
                    }
                }
                stack.push(i);
            }
            return res;
        }
    }

}
