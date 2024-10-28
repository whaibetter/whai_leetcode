package cn.whaifree.LCR;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/21 11:35
 * @注释
 */
public class LCR001 {

    @Test
    public void test() {
        int a = 15;
        int b = 2;
        int c = new Solution1().divide(a, b);
        System.out.println(c);
    }

    class Solution {
        /**
         * 位运算
         * @param a
         * @param b
         * @return
         */
        public int divide(int a, int b) {
            /**
             * 17/3
             * 3<2 2次 一直到 24-12 ，之间有2^2=4个3，剩下的5再 3<2 只要0次，则5中有2^0个3
             */
            boolean reverse = false;
            if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
                reverse = true;
            }
            if (a < 0) {
                a = -a;
            }
            if (b < 0) {
                b = -b;
            }


            int down = 0;
            int sum = 0;
            int tmp = a;
            while (tmp > 0) {
                sum += down;
                tmp -= down * b;
                down = getDown(tmp, b);
            }

            return reverse ? -sum : sum;

        }


        /**
         *
         * @param beDivide
         * @param divide
         * @return beDivide包含几个divide
         */
        public int getDown(int beDivide, int divide) {

            int have = 0;
            int tmp = divide;
            while (tmp < beDivide) {
                have++;
                tmp = tmp << 2;
            }
            return 1 << have;
        }
    }

    class Solution1 {
        public int divide(int a, int b) {
            //特殊情况1, b=1
            if (b == 1){
                return a;
            }
            //特殊情况2, b=-1
            if (b == -1){
                return a == Integer.MIN_VALUE ? Integer.MAX_VALUE : -a;
            }
            //特殊情况3, a=0
            if (a == 0){
                return 0;
            }

            //确定符号
            boolean positive = (a ^ b) >= 0;
            //为避免溢出, 转换为负数进行计算
            a = a < 0 ? a : -a;
            b = b < 0 ? b : -b;
            //快速相减

            /**
             *  17/3
             *  3<2 2次 一直到 24-12 ，之间有2^2=4个3，剩下的5再 3<2 只要0次，则5中有2^0个3
             */
            int quotient = 0;
            while (a <= b){
                int base = 1;

                int divisor = b;
                //使用减法, 避免溢出
                while (a - divisor <= divisor){
                    divisor <<= 1; // 被除数*2  b=2时 就是 2 4 8 16 不断扩大，直到达到a的一半
                    base <<= 1;    // 表示包含的（b 被除数）的个数，后面给他加到quotient中
                }
                quotient += base;
                a -= divisor; // a减去前面已经找到的部分 即 17 找到了前面的12，就17-12=5
            }
            return positive ? quotient : -quotient;
        }
    }


    class Solution2 {
        public int divide(int a, int b) {

            // 不考虑越界的写法
            int res = 0;
            while (a < b) {
                int count = 1;//记住有几个要加入
                int copyB = b; // 记录每次的总量
                while (a - copyB < copyB) {
                    copyB <<= 1;
                    count <<= 1;
                }
                res += count;
                a -= copyB;
            }
            return res;
        }
    }

}
