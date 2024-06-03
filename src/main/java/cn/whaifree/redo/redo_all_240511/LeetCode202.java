package cn.whaifree.redo.redo_all_240511;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/2 16:25
 * @注释
 */
public class LeetCode202 {

    @Test
    public void test() {
        Solution solution = new Solution();
        boolean happy = solution.isHappy(19);
        System.out.println(happy);
    }

    class Solution {
        /**
         * 2 4 16 37 56 61  37 出现循环
         * @param n
         * @return
         */
        public boolean isHappy(int n) {
            int tmp = n;
            Set<Integer> set = new HashSet<>();
            while (tmp != 1) {
                set.add(tmp);
                tmp = get(tmp);
                if (set.contains(tmp)) {
                    return false;
                }
            }
            return true;
        }

        public int get(int num) {
            int sum = 0;
            while (num != 0) {
                int retail = num % 10;
                num /= 10;
                sum += retail * retail;
            }
            return sum;
        }
    }
}
