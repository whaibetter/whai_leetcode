package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/29 0:15
 * @注释
 */
public class LeetCode69 {

    @Test
    public void test() {
        System.out.println(new Solution().mySqrt(8));
    }

    class Solution {
        /**
         * p2=x
         * p=x1/2
         * log x p = 1/2
         * lnx / lnp = 1/2
         * lnp = 2 lnx
         * p = e(2Lnx)
         * p = log p
         *
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            int left = 0;
            int right = x;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if ((long) mid * mid <= x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return right;
        }
    }
}
