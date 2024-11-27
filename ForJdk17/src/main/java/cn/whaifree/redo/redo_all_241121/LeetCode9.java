package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/24 17:06
 * @注释
 */
public class LeetCode9 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(123));
    }

    class Solution {
        /**
         * 12321
         * @param x
         * @return
         */
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            // 反转后相等？
            return reverse(x) == x;
        }

        public int reverse(int x) {

            int res = 0;
            while (x != 0) {
                res *= 10;
                res += x % 10;
                x /= 10;
            }
            return res;
        }
    }

}
