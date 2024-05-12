package cn.whaifree.redo.redo.redo_24_4_6;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/6 12:35
 * @注释
 */
public class LeetCode11 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[] height = {1,1};
        int i = solution.maxArea(height);
        System.out.println(i);
    }

    class Solution {
        public int maxArea(int[] height) {

            // capacity = (right-left)*min(right,left)
            // 移动短的，可能变大，不变，变小
            // 移动长的，只会变小
            int left = 0;
            int right = height.length - 1;
            int capacity = Integer.MIN_VALUE;
            while (left < right) {
                capacity = Math.max(capacity, (right - left) * Math.min(height[left], height[right]));
                if (height[left] < height[right]) {
                    left++;
                }else {
                    right--;
                }
            }
            return capacity;
        }
    }

}
