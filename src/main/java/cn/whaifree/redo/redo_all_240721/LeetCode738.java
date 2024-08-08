package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/8 22:21
 * @注释
 */
public class LeetCode738 {
    @Test
    public void test()
    {
        Solution solution = new Solution();

        System.out.println(solution.monotoneIncreasingDigits(13211432));
        System.out.println(solution.monotoneIncreasingDigits(299999));
        System.out.println(solution.monotoneIncreasingDigits(555555));
        System.out.println(solution.monotoneIncreasingDigits(23456));
        System.out.println(solution.monotoneIncreasingDigits(1254321));
        System.out.println(solution.monotoneIncreasingDigits(332));

        System.out.println(Integer.parseInt("0999"));

    }

    class Solution {
        /**
         * 13211432
         * 09999999
         *
         * 5432
         * 4999
         * @param n
         * @return
         */
        public int monotoneIncreasingDigits(int n) {
            String s = String.valueOf(n);
            byte[] bytes = s.getBytes();
            int start = Integer.MAX_VALUE;
            for (int i = bytes.length - 1; i > 0; i--) {
                byte after = bytes[i];
                byte before = bytes[i - 1];
                if (after < before) {
                    bytes[i - 1] -= 1;
                    start = i;
                }
            }

            if (start == Integer.MAX_VALUE) {
                return n;
            }

            for (int i = start; i < bytes.length; i++) {
                bytes[i] = '9';
            }

            return Integer.parseInt(new String(bytes));
        }

    }
}
