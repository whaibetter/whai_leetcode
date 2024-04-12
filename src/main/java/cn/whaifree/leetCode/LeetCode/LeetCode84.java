package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/11 13:42
 * @注释
 */
public class LeetCode84 {

    @Test
    public void test()
    {
        int[] heights = {0};
        int i = new Solution1().largestRectangleArea(heights);
        System.out.println(i);
    }

    class Solution {
        public int largestRectangleArea(int[] heights) {
            int[] left = new int[heights.length];
            int[] right = new int[heights.length];

            // 记录左右第一个小于i的坐标，这样就能计算以heights[i]为高的面积
            left[0] = -1;
            for (int i = 1; i < heights.length; i++) {
                int index = i - 1;
                while (index >= 0 && heights[index] >= heights[i]) {
                // index--;  这样会超时
                    index = left[index];
                }
                left[i] = index;
            }

            right[right.length - 1] = right.length;
            for (int i = right.length - 2; i >= 0; i--) {
                int index = i + 1;
                while (index < right.length && heights[index] >= heights[i]) {
                    index = right[index];
                }
                right[i] = index;
            }

            // 获得左边右边比他小的第一个数，就能计算最大面积
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < left.length; i++) {
                int sum = (right[i] - left[i] - 1) * heights[i];
                max = Math.max(max, sum);
            }

            return max;
        }

    }

    class Solution1 {
        /**
         * 找每个柱子左右两边第一个小于该柱子的柱子
         *
         * 找到凸的地方
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            // 数组扩容，在头和尾各加入一个元素，因为要计算以i为高的最大面积
            int [] newHeights = new int[heights.length + 2];
            newHeights[0] = 0;
            newHeights[newHeights.length - 1] = 0;
            for (int index = 0; index < heights.length; index++){
                newHeights[index + 1] = heights[index];
            }
            heights = newHeights;
            // 扩容是为了在计算最大矩形面积时能处理以下两种特殊情况：
            // 包含第一个柱子的最大矩形：
            // 包含最后一个柱子的最大矩形：

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int res = 0;
            for (int i = 1; i < heights.length; i++) {
                if (heights[i] == heights[stack.peek()]) {
                    // 如果 栈顶 比 i 小
                    stack.push(i);
                } else if (heights[i] < heights[stack.peek()]) {
                    // 4 栈顶[6 3...]
                    while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                        // 顶和栈顶的下一个元素以及要入栈的三个元素组成了我们要求最大面积的高度和宽度
                        Integer mid = stack.pop(); // 凸的中间
                        if (!stack.isEmpty()) {
                            Integer left = stack.peek();
                            Integer right = i;
                            res = Math.max(res, (right - left - 1) * heights[mid]);
                        }
                    }

                    stack.push(i);
                } else {
                    stack.push(i);
                }
            }
            return res;
        }

    }
}
