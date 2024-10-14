package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/14 15:57
 * @注释
 */
public class LeetCode84_1 {
    @Test
    public void test() {
        int[] heights = {2,1,2};
        Solution solution = new Solution();
        int max = solution.largestRectangleArea(heights);
        System.out.println(max);
    }

    class Solution {
        /**
         * 前后遍历，找到每个位置左右第一个小于他的
         * @param heights
         * @return
         */
        public int largestRectangleArea(int[] heights) {
            int[] tmp = heights;

            int[] right = new int[tmp.length];
            int[] left = new int[tmp.length];
            left[0] = -1;

            for (int i = 0; i < tmp.length ; i++) {
                int index = i - 1;
                while (index >= 0 && tmp[index] >= tmp[i]) {
                    index = left[index];
                }
                left[i] = index;
            }
            right[tmp.length - 1] = tmp.length;
            for (int i = tmp.length - 2; i >= 0; i--) {
                int index = i + 1;
                while (index < tmp.length && tmp[index] >= tmp[i]) {
                    index = right[index];
                }
                right[i] = index;
            }

            int max = 0;
            for (int i = 0; i < tmp.length; i++) {
                max = Math.max(max, (right[i] - left[i] - 1) * tmp[i]);
            }
            return max;
        }
    }
}
