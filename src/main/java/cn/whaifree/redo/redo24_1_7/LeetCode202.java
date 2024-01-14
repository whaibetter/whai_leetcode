package cn.whaifree.redo.redo24_1_7;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LeetCode202 {

    @Test
    public void test() {
        System.out.println(new Solution().isHappy(2));
    }

    class Solution {
        /**
         * 无限循环
         * @param n
         * @return
         */
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (n != 1) {
                if (set.contains(n)) {
                    return false;
                }
                set.add(n);
                n = get(n);
            }
            return true;
        }

        public int get(int n) {
            int sum = 0;
            while (n != 0) {
                int e = n % 10;
                sum += e * e;
                n /= 10;
            }
            return sum;
        }
    }
}
