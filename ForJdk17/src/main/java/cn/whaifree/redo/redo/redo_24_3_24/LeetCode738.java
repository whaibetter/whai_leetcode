package cn.whaifree.redo.redo.redo_24_3_24;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 15:43
 * @注释
 */
public class LeetCode738 {

    @Test
    public void test() {
        int n =10;
        Solution solution = new Solution();
        System.out.println(solution.monotoneIncreasingDigits(n));
    }

    class Solution {
        public int monotoneIncreasingDigits(int n) {
            String s = String.valueOf(n);
            char[] chars = s.toCharArray();
            /**
             * 98
             * 89
             */
            int index = chars.length;
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] < chars[i - 1]) {
                    chars[i - 1] -= 1;
                    index = i-1;
                }
            }

            for (int i = index + 1; i < chars.length; i++) {
                chars[i] = '9';
            }

            return Integer.parseInt(new String(chars));
        }

    }
}
