package cn.whaifree.redo.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 11:10
 * @注释
 */
public class LeetCode42 {
    @Test
    public void test()
    {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new Solution1().trap(height));
    }

    class Solution {
        public int trap(int[] height) {
            // 接下雨水，寻找凹陷处
            Deque<Integer> stack = new LinkedList<>();
           /* int[] he = new int[height.length + 2];
            for (int i = 1; i < height.length + 1; i++) {
                he[i] = height[i - 1];
            }
            height = he;*/

            stack.push(0);
            int res = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[stack.peek()] < height[i]) {
                    while (!stack.isEmpty()&&height[stack.peek()] < height[i]) {
                        int mid = stack.pop();
                        if (!stack.isEmpty()) {
                            int right = i;
                            int left = stack.peek();
                            int w = right - left - 1;
                            int h = (Math.min(height[left], height[right]) - height[mid]);
                            res += w * h;
                        }
                    }
                    stack.push(i);
                }else {
                    stack.push(i);
                }

            }
            return res;
        }
    }

    class Solution1 {
        public int trap(int[] height) {

            int[] left = new int[height.length];
            int[] right = new int[height.length];
            // 从左到右获取左边左边最小
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < height.length; i++) {
                max = Math.max(max, height[i]);
                left[i] = max;
            }
            max = Integer.MIN_VALUE;
            for (int i = height.length - 1; i >= 0; i--) {
                max = Math.max(max, height[i]);
                right[i] = max;
            }
            int res = 0;
            for (int i = 0; i < height.length; i++) {
                res += (Math.min(left[i], right[i]) - height[i]);
            }
            return res;
        }
    }

}
