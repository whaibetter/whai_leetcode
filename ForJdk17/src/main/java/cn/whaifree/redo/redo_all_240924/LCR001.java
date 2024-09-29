package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/27 11:25
 * @注释
 */
public class LCR001 {

    @Test
    public void test() {
        System.out.println(new Solution().divide(-2147483648, 1));
    }


    class Solution {
        /**
         * 17/3
         * 17 << 3<<2 = 5  5 << 0 余数2
         * 2^2+2^0 // 向上取整
         *
         * @param a
         * @param b
         * @return
         */
        public int divide(int a, int b) {
            // 特殊情况2, b=-1
            if (b == -1) {
                return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
            }
            long A = a;
            long B = b;
            boolean reverse = false;
            if ((A <= 0 && B <= 0) || (A >= 0 && B >= 0)) {
                reverse = true;
            }
            A = Math.abs(A);
            B = Math.abs(B);

            long base = A;
            long res = 0;
            while (base >= B) {
                long tmpPoint = 1;
                long item = B;
                while ((item << 2) < base) {
                    item <<= 1;
                    tmpPoint <<= 1;
                }
                base -= item;
                res += tmpPoint;
            }


            return (int) (reverse ? res : -res);
        }
    }

}
