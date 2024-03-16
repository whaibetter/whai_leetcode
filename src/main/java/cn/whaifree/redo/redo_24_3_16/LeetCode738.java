package cn.whaifree.redo.redo_24_3_16;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 18:04
 * @注释
 */
public class LeetCode738 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int n = 668799 ;
        int n1 = 668799;
        System.out.println(solution.monotoneIncreasingDigits(n));
    }

    class Solution {
        public int monotoneIncreasingDigits(int n) {

            // 668841 668799

            // 遍历的过程-1 ，再找到最后一个递减的然后 后面全部替换为9
            char[] chars = String.valueOf(n).toCharArray();
            int index = Integer.MAX_VALUE;
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] < chars[i - 1]) {
                    chars[i-1]--;
                    index = i;
                }
            }

            for (int i = index; i < chars.length ; i++) {
                chars[i] = '9';
            }

            return Integer.parseInt(new String(chars));
        }

    }


}
