package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 15:29
 * @注释
 */
public class LeetCode202 {

    @Test
    public void test() {
        Solution solution = new Solution();
        boolean happy = solution.isHappy(2);
        System.out.println(happy);
    }

    class Solution {
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (n > 1) {
                if (set.contains(n)) return false;
                set.add(n);
                n = get(n);
            }
            return true;
        }

        public int get(int n) {
            int res = 0;
            while (n > 0) {
                int retail = n % 10;
                res += retail * retail;
                n /= 10;
            }
            return res;
        }
    }
}
