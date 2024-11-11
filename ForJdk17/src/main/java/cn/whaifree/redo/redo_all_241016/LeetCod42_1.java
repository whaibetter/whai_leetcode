package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/11 10:36
 * @注释
 */
public class LeetCod42_1 {

    @Test
    public void test() {
        int[] height = {4,2,0,3,2,5};
        int trap = new Solution().trap(height);
        System.out.println(trap);
    }

    class Solution {
        /**
         * 每个位置左边第一个比他大的
         * @param height
         * @return
         */
        public int trap(int[] height) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(0);
            int res = 0;
            for (int i = 1; i < height.length; i++) {
                while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                    // 单调递增
                    Integer pop = stack.pop();
                    int level = height[pop];
                    if (!stack.isEmpty()) {
                        int left = stack.peek();
                        int right = i;
                        int sub = Math.min(height[left], height[right]) - level;
                        res += sub * (right - left - 1);
                    }
                }
                stack.push(i);
            }

            return res;
        }
    }

}
