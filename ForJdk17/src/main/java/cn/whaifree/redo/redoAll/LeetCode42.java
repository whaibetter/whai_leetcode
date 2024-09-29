package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 17:29
 * @注释
 */
public class LeetCode42 {

    @Test
    public void test(){

        System.out.println(new Solution().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    class Solution {
        public int trap(int[] height) {
            int[] leftHeight = new int[height.length];

            int tmpLeftHeight = 0;
            for (int i = 0; i < height.length; i++) {
                int n = height[i];
                tmpLeftHeight = Math.max(tmpLeftHeight, n);
                leftHeight[i] = tmpLeftHeight;
            }
            int[] rightHeight = new int[height.length];
            int tmpRightHeight = 0;
            for (int i = height.length - 1; i >= 0; i--) {
                int n = height[i];
                tmpRightHeight = Math.max(tmpRightHeight, n);
                rightHeight[ i ] = tmpRightHeight;
            }

            int res = 0;
            for (int i = 0; i < height.length; i++) {
                int left = leftHeight[i];
                int right = rightHeight[i];
                res += (Math.min(left, right) - height[i]);
            }

            return res;
        }
    }
}
