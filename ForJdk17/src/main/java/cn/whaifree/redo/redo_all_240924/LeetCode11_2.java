package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/14 15:48
 * @注释
 */
public class LeetCode11_2 {
    @Test
    public void test() {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        Solution solution = new Solution();
        int result = solution.maxArea(height);
        System.out.println(result);
    }

    class Solution {
        public int maxArea(int[] height) {

            int left = 0;
            int right = height.length - 1;
            int maxSize = 0;
            while (left < right) {
                maxSize = Math.max(maxSize, (right - left) * Math.min(height[left], height[right]));
                if (height[left] < height[right]) {
                    left++;
                }else {
                    right--;
                }
            }
            return maxSize;
        }


    }
}
