package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/28 9:52
 * @注释
 */
public class LeetCode7 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int res = solution.reverse(1534236469);
        System.out.println(res);
        res = solution.reverse(-123);
        System.out.println(res);
        res = solution.reverse(120);
        System.out.println(res);
        res = solution.reverse(0);
        System.out.println(res);
    }

    class Solution {
        public int reverse(int x) {
            String s = String.valueOf(x);
            char[] charArray = s.toCharArray();
            int left = 0;
            if (charArray[0] == '-') {
                left = 1;
            }

            int right = charArray.length - 1;
            StringBuilder sb = new StringBuilder();
            while (right >= left) {
                sb.append(charArray[right]);
                right--;
            }
            try {
                int res = Integer.parseInt(sb.toString());
                if (left == 1) {
                    res = -res;
                }

                return res;
            } catch (NumberFormatException e) {
                return 0;
            }

        }
    }


    @Test
    public void test1() {
        Solution1 solution1 = new Solution1();
        int res = solution1.reverse(-134236469);
        System.out.println(res);
    }

    class Solution1 {
        public int reverse(int x) {

            int res = 0;
            while (x != 0) { // 正负都可以进入
                if (res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                if (res < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                int retail = x % 10; // 余数
                x /= 10;
                res = res * 10 + retail;
            }
            return res;
        }
    }
}
