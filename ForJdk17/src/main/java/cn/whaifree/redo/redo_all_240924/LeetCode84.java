package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/29 13:33
 * @注释
 */
public class LeetCode84 {

    @Test
    public void test() {
        int[] heights = {2,1,2};
        System.out.println(new Solution1().largestRectangleArea(heights));
    }

    class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] newHe = new int[heights.length];
            int[] left = new int[heights.length];
            int[] right = new int[heights.length];

            int nowMinIndex = 0;
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
            for (int i = heights.length - 2; i > 0; i--) {
                int index = i + 1;
                while (index <= heights.length - 1 && heights[index] >= heights[i]) {
                    index = right[index];
                }
                right[i] = index;
            }

            int max = 0;
            for (int i = 0; i < right.length; i++) {
                int l = left[i];
                int r = right[i];
                max = Math.max(max, (r - l - 1) * heights[i]);
            }
            return max;
        }
    }

    class Solution1 {
        public int largestRectangleArea(int[] heights) {
            int[] h = new int[heights.length + 2];
            System.arraycopy(heights, 0, h, 1, heights.length);
            heights = h;

            Deque<Integer> stack = new LinkedList<>();

            int res = 0;
            for (int i = 0; i < heights.length; i++) {
                int he = heights[i];
                while (!stack.isEmpty() && he < heights[stack.peek()]) {
                    Integer pop = stack.pop();
                    if (stack.isEmpty()) {
                        continue;
                    }
                    int right = i;
                    int left = stack.peek(); // left  right 不包含
                    int region = (right - left - 1) * heights[pop];
                    res = Math.max(res, region);
                }
                stack.push(i);
            }
            return res;
        }
    }
}
