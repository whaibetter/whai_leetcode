package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/17 22:32
 * @注释
 */
public class LCR001 {

    public static void main(String[] args) {



    }

    @Test
    public void test() {

        System.out.println(new Solution().divide(-2147483648, -1));
    }

    class Solution {
        /**
         * b<<1
         *
         * 让被除数不断翻倍去近似接近
         *
         * 17/3
         * 3<<1 6<<1 12<<1 (17-12=5)
         * 5/3
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


            // 确定符号
            boolean positive = (A ^ B) >= 0;
            // 为避免溢出, 转换为负数进行计算 -2^31 <= a, b <= 2^31 - 1
            A = A < 0 ? -A : A;
            B = B < 0 ? -B : B;

            long tmpA = A;
            long tmpB = B;

            long mulSum = 0;
            while (tmpA >= B) {
                long mul = 1;
                tmpB = B;
                while (tmpB << 1 < tmpA) {
                    tmpB <<= 1;
                    mul <<= 1;
                }
                mulSum += mul;
                tmpA -= tmpB;
            }

            return (int) (positive ? mulSum : -mulSum);
        }
    }
}
