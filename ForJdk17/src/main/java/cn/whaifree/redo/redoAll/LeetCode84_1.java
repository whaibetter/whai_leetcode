package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/2 22:32
 * @注释
 */
public class LeetCode84_1 {

    @Test
    public void test() {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(new Solution().largestRectangleArea(heights));
    }

    class Solution {
        public int largestRectangleArea(int[] heights) {
           // 每个元素，找到左右第一个比他小的元素
            int[] leftMin = new int[heights.length];
            int[] rightMin = new int[heights.length];

            leftMin[0] = -1;
            for (int i = 1; i < heights.length; i++) {
                int nowHei = heights[i];
                int index = i - 1;
                while (index >= 0 && heights[index] >= nowHei) {
                    index = leftMin[index];
                }
                leftMin[i] = index;
            }

            rightMin[heights.length - 1] = heights.length;
            for (int i = heights.length - 2; i >= 0; i--) {
                int nowHei = heights[i];
                int index = i + 1;
                while (index <= heights.length - 1 &&heights[index] >= nowHei) {
                    index = rightMin[index];
                }
                rightMin[i] = index;
            }

            int max = 0;
            for (int i = 0; i < rightMin.length; i++) {
                max = Math.max(max, heights[i] * (rightMin[i] - leftMin[i] - 1));
            }
            return max;

        }
    }

    class Solution1 {
        /**
         * 单调栈
         *
         * 有凹凸， \_/这种 每次弹出就可以计算一次值
         *
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {

            int[] ints = new int[heights.length + 2];
            System.arraycopy(heights, 0, ints, 1, heights.length);
            heights = ints;

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            int max = 0;
            for (int i = 1; i < heights.length; i++) {
                int now = heights[i];
                if (heights[stack.peek()] > now) {
                    while (!stack.isEmpty() && now < heights[stack.peek()]) {
                        /**
                         *
                         *
                         * 2 3 1
                         *
                         */
                        Integer pop = stack.pop();
                        if (!stack.isEmpty()) {
                            max = Math.max(max, (i - stack.peek() - 1) * heights[pop]);
                        }
                    }

                }
                stack.push(i);

            }

            return max;
        }
    }
}
