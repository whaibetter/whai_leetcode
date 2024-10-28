package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/26 11:32
 * @注释
 */
public class LeetCode42 {
    @Test
    public void test() {
        int[] height = {4,2,0,3,2,5};
        int res = new Solution().trap(height);
        System.out.println(res);
    }
    class Solution {
        /**
         * 对每个位置，找到左边第一个比他大的，右边第一个比他大的i
         * @param height
         * @return
         */
        public int trap(int[] height) {
            int[] leftMax = new int[height.length];
            int[] rightMax = new int[height.length];

            int leftMaxIndex = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[leftMaxIndex] < height[i]) {
                    leftMaxIndex = i;
                }
                leftMax[i] = height[leftMaxIndex];
            }

            int rightMaxIndex = height.length - 1;
            for (int i = height.length - 1; i >= 0; i--) {
                if (height[rightMaxIndex] < height[i]) {
                    rightMaxIndex = i;
                }
                rightMax[i] = height[rightMaxIndex];
            }

            int res = 0;
            for (int i = 0; i < height.length; i++) {
                res += (Math.min(rightMax[i], leftMax[i]) - height[i]);
            }
            return res;
        }
    }

    @Test
    public void test1() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = new Solution1().trap(height);
        System.out.println(res);
    }

    class Solution1 {
        /**
         *
         * @param height
         * @return
         */
        public int trap(int[] height) {
            int res = 0;
            Deque<Integer> stack = new LinkedList<>(); // 从下到上递减
            for (int i = 0; i < height.length; i++) {
                int now = height[i];

//                if (!stack.isEmpty() && height[stack.peek()] > now) {
//
//                } else
                if (!stack.isEmpty() &&height[stack.peek()] < now) {
                    while (!stack.isEmpty() && height[stack.peek()] < now) {
                        Integer pop = stack.pop();
                        // 出栈,遇到凹
                        if (!stack.isEmpty()) {
                            int leftIndex = stack.peek();
                            int rightIndex = i;
                            int leftHeight = height[leftIndex];
                            int rightHeight = height[rightIndex];
                            int nowHeight = height[pop]; // 凹
                            int width = rightIndex - leftIndex - 1;
                            res += (Math.min(leftHeight, rightHeight) - nowHeight) * width;
                        }
                    }
                }
                stack.push(i);
            }
            return res;
        }
    }

}
