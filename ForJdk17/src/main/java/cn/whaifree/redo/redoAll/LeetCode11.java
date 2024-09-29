package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/24 2:14
 * @注释
 */
public class LeetCode11 {

    @Test
    public void test() {
        int[] height = {1,1};
        System.out.println(new Solution().maxArea(height));
    }

    class Solution {
        /**
         *
         * 不断移动短线
         *
         * @param height
         * @return
         */
        public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;

            int cap = Integer.MIN_VALUE;

            while (left <= right) {
                cap = Math.max(cap, (right - left) * Math.min(height[right], height[left]));
                if (height[left] < height[right]) {
                    left++;
                }else {
                    right--;
                }
            }
            return cap;
        }
    }

}
