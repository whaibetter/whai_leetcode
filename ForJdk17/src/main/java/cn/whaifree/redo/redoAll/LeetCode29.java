package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 14:45
 * @注释
 */
public class LeetCode29 {

    @Test
    public void test(){

        Solution solution = new Solution();
        int i = solution.divide(-14,7 );
        System.out.println(i);

    }

    class Solution {

        /**
         *
         * 17/3  3<2 12  5 3<0
         *
         * 2^2 + 2^0 = 5
         *
         *
         * 10 / 3
         *
         * 3<1 6 4 3<0
         *
         * 2^1+2^0
         *
         * @param dividend
         * @param divisor
         * @return
         */
        public int divide(int dividend, int divisor) {


            if (dividend == 0) {
                return 0;
            }
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            boolean negative;
            negative = (dividend ^ divisor) <0;//用异或来计算是否符号相异

            long Ldividend = Math.abs((long) dividend);
            long Ldivisor = Math.abs((long) divisor);

            int sumPow = 0;
            while (Ldividend >= Ldivisor) {
                int pow = 0;
                while ((Ldivisor << (pow + 1)) < Ldividend) {
                    pow++;
                }
                Ldividend -= Ldivisor << pow;
                sumPow += (int) Math.pow(2, pow);
            }
            return negative ? -sumPow : sumPow;
        }
    }
}
