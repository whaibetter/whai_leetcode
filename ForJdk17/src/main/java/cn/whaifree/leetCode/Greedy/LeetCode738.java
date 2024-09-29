package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/6 15:24
 * @注释
 */
public class LeetCode738 {

    @Test
    public void test() {
        System.out.println(new Solution().monotoneIncreasingDigits(65832));
    }


    class Solution {

        /**
         * 98，一旦出现strNum[i - 1] > strNum[i]的情况（非单调递增），首先想让strNum[i - 1]减一，strNum[i]赋值9（因为要最大的数，用9一定满足非递减），这样这个整数就是89
         * @param N
         * @return
         */
        public int monotoneIncreasingDigits(int N) {
            String[] strings = (N + "").split("");

            int start = strings.length;

            // 出现递减的两个 前一个-1，找到最先递减的的index，index后面全部为9
            for (int i = strings.length - 1; i > 0; i--) {
                if (Integer.parseInt(strings[i]) < Integer.parseInt(strings[i - 1])) {
                    strings[i - 1] = (Integer.parseInt(strings[i - 1]) - 1) + "";
                    start = i;
                }
            }


            for (int i = start; i < strings.length; i++) {
                strings[i] = "9";
            }
            return Integer.parseInt(String.join("",strings));
        }
    }

    class Solution1 {
        public int monotoneIncreasingDigits(int n) {
            String s = String.valueOf(n);
            char[] chars = s.toCharArray();
            int start = s.length();
            for (int i = s.length() - 2; i >= 0; i--) {
                if (chars[i] > chars[i + 1]) {
                    chars[i]--;
                    start = i+1;
                }
            }
            for (int i = start; i < s.length(); i++) {
                chars[i] = '9';
            }
            return Integer.parseInt(String.valueOf(chars));
        }
    }

}
