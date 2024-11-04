package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 17:18
 * @注释
 */
public class LeetCode69 {

    @Test
    public void test() {
        Solution1 solution = new Solution1();
        int i = solution.mySqrt(8);
        System.out.println(i);
    }

    class Solution1 {
        public int mySqrt(int x) {
            int left = 0;
            int right = x;
            while (left <= right) {
                int mid = (left + right) / 2;
                long pro = (long) mid * mid;
                if (pro > x) {
                    right = mid - 1;
                }else if (pro < x){
                    left = mid + 1;
                }else {
                    return mid;
                }
            }
            return right;
        }
    }

    class Solution {
        /**
         *
         * x^1/2 =k
         * k^2 = x
         * logk x = 2
         * ln k
         * ln x = 2
         *
         * ln k = 2 ln x
         * e(ln k) = e2lnx = x2
         *
         * @param x
         * @return
         */
        public int mySqrt(int x) {
            double log = Math.log(x);
            log /= 2;
            int ans = (int) Math.exp(log);
            if ((long) (ans + 1) * (ans + 1) <= x) {
                return ans + 1;
            }
            return ans;

        }
    }
}
