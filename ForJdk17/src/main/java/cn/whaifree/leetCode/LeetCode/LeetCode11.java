package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/3 17:35
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
        /**
         * $S(i,j)=min(h[i],h[j])*(j-i)$
         * 双指针
         * 长板移动面积不变或 变小
         * 短板移动 变大 或不变
         *      不断移动短板
         * @param height
         * @return
         */
        public int maxArea(int[] height) {

            int storage = 0;
            int left = 0;
            int right = height.length - 1;
            while (left < right) {
                storage = Math.max(getArea(height, left, right), storage);
                if (height[left] < height[right]) {
                    left++;
                }else {
                    right--;
                }
            }
            return storage;
        }

        public int getArea(int[] height, int start, int end) {
            return Math.min(height[start], height[end]) * (end - start);
        }
    }
}
