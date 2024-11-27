package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/24 13:57
 * @注释
 */
public class LeetCode7 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.reverse(123));
    }

    class Solution {
        public int reverse(int x) { // 123
            int res = 0;
            while (x != 0) { // 正负都可以进入
                if (res > Integer.MAX_VALUE / 10) {
                    return 0;
                }
                if (res < Integer.MIN_VALUE / 10) {
                    return 0;
                }
                int retail = x % 10; // 余数 3
                x /= 10; // 12
                res = res * 10 + retail; //
            }
            return res;
        }
    }

}
