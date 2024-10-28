package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/28 14:36
 * @注释
 */
public class LeetCode9 {

    class Solution {
        public boolean isPalindrome(int x) {
            String s = String.valueOf(x);
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome(5));
        System.out.println(solution.isPalindrome(-121));
    }

    class Solution1 {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }

            int A = x;
            int res = 0;
            while (A != 0) {
                res = res * 10 + A % 10;
                A /= 10;
            }
            return x == res;
        }

    }

}
