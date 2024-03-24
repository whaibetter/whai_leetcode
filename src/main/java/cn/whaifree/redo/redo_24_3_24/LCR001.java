package cn.whaifree.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 12:16
 * @注释
 */
public class LCR001 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int a = -2147483648;
        int b = 2;
        int res = solution.divide(a, b);
        System.out.println(res);
    }

    class Solution {
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

            /**
             * 17/3
             *
             * 17 3<<1=6 6<<1=12 12<<1=24 2^2
             * 5  3<<1=6 2^0
             *
             */

            //确定符号
            boolean positive = (a ^ b) >= 0;
            //为避免溢出, 转换为负数进行计算 -2^31 <= a, b <= 2^31 - 1
            a = a < 0 ? a : -a;
            b = b < 0 ? b : -b;


            int res = 0;
            while (a <= b) {

                int i = b; // 标记被除数的变化，不断x2
                int tmpRes = 1; // 记录包含了多少个b
                while (a - i <= i) {
                    i <<= 1;
                    tmpRes <<= 1;
                }
                res += tmpRes;
                a = a - i;
            }

            return positive ? res : -res;
        }


    }

    class Solution1 {
        /**
         * 没有转为负数处理会错误
         * 输入
         * a =-2147483648
         * b =2
         * 输出
         * 0
         * 预期结果
         * -1073741824
         * @param a
         * @param b
         * @return
         */
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

            /**
             * 17/3
             *
             * 17 3<<1=6 6<<1=12 12<<1=24 2^2
             * 5  3<<1=6 2^0
             *
             */

            //确定符号
            boolean positive = (a ^ b) >= 0;
            //为避免溢出, 转换为负数进行计算 -2^31 <= a, b <= 2^31 - 1
            a = a < 0 ? -a : a;
            b = b < 0 ? -b : b;


            int res = 0;
            while (a >= b) {

                int i = b; // 标记被除数的变化，不断x2
                int tmpRes = 1; // 记录包含了多少个b
                while (a - i >= i) {
                    i <<= 1;
                    tmpRes <<= 1;
                }
                res += tmpRes;
                a = a - i;
            }

            return positive ? res : -res;
        }


    }

}
