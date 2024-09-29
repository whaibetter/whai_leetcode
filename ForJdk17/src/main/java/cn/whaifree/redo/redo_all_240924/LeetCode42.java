package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/29 12:15
 * @注释
 */
public class LeetCode42 {
    @Test
    public void test() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Solution1().trap(height));
    }

    class Solution {
        /**
         *
         * 记录每个位置左边的最高值
         * 记录每个位置右边的最高值
         * @param height
         * @return
         */
        public int trap(int[] height) {
            int tmpLeftHeight = 0;
            int[] left = new int[height.length + 2];
            for (int i = 0; i < height.length; i++) {
                tmpLeftHeight = Math.max(tmpLeftHeight, height[i]);
                left[i + 1] = tmpLeftHeight;
            }

            int tmpRightHeight = 0;
            int[] right = new int[height.length + 2];
            for (int i = height.length - 1; i >= 0; i--) {
                tmpRightHeight = Math.max(tmpRightHeight, height[i]);
                right[i + 1] = tmpRightHeight;
            }

            int res = 0;
            for (int i = 0; i < height.length; i++) {
                int a = left[i + 1];
                int b = right[i + 1];
                res += Math.min(a, b) - height[i];
            }
            return res;
        }
    }


    class Solution1 {
        /**
         * 单调栈，找到右边第一个比他高的元素
         * @param height
         * @return
         */
        public int trap(int[] height) {

            Deque<Integer> stack = new LinkedList<>();
            int res = 0;
            for (int i = 0; i < height.length; i++) {
                int item = height[i];
                if (stack.isEmpty() || height[stack.peek()] > item) {
                    stack.push(i);
                } else {
                    while (!stack.isEmpty() && height[stack.peek()] < item) {
                        Integer pop = stack.pop();
                        if (stack.isEmpty()) {
                            continue;
                        }
                        int right = i;
                        // 2 0 1 4
                        // l   p r 行成凹
                        int left = stack.peek();
                        int width = right - left - 1;
                        int heigh = Math.min(height[left], height[right]) - height[pop];
                        res += width * heigh;
                    }
                    stack.push(i);
                }

            }
            return res;
        }
    }
}
