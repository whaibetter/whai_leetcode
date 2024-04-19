package cn.whaifree.redo.redo_24_4_20;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/17 11:06
 * @注释
 */
public class LeetCode69 {
    @Test
    public void test()
    {

        int i = new Solution1().mySqrt(10);
        System.out.println(i);
    }



    class Solution {
        /**
         * <img src="http://42.192.130.83:9000/picgo/imgs/image-20240417112028966.png">
         * x^(1/2) = p
         * 1/2 = log x p = ln x / ln p
         * ln p = 2 ln x
         * p = e^(2lnx)
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }
            // 用1/2 用例为9会输出2，错误
            int exp = (int) Math.exp( 1d/2 * Math.log(x));
            if ((long) (exp + 1) * (exp + 1) <= x) { // 8--> exp = 1
                return exp + 1;
            }
            return exp;
        }
    }

    class Solution1 {

        /**
         * 二分查找 12
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            int left = 0;
            int right = x;
            int ans = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if ((long) mid * mid > x) {
                    right = mid - 1;
                } else {
                    ans = mid;
                    left = mid + 1;
                }
            }
            return ans;
        }

    }
}
