package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 0:11
 * @注释
 */
public class LeetCode13 {

    @Test
    public void test()
    {
        Solution1 solution = new Solution1();
        int i = solution.romanToInt("MCMXCIV");
        System.out.println(i);
    }

    class Solution {
        public int romanToInt(String s) {
            char[] charArray = s.toCharArray();
            int res = 0;
            for (int i = 0; i < charArray.length; i++) {
                if (i < charArray.length - 1 && charArray[i] == 'I' && charArray[i + 1] == 'V') {
                    res += 4;
                    i++;
                }
                else if (i < charArray.length - 1 && charArray[i] == 'I' && charArray[i + 1] == 'X') {
                    res += 9;
                    i++;
                }
                else if (i < charArray.length - 1 && charArray[i] == 'X' && charArray[i + 1] == 'L') {
                    res += 40;
                    i++;
                }
                else if (i < charArray.length - 1 && charArray[i] == 'X' && charArray[i + 1] == 'C') {
                    res += 90;
                    i++;
                }
                else if (i < charArray.length - 1 && charArray[i] == 'C' && charArray[i + 1] == 'D') {
                    res += 400;
                    i++;
                }
                else if (i < charArray.length - 1 && charArray[i] == 'C' && charArray[i + 1] == 'M') {
                    res += 900;
                    i++;
                }
                else if (charArray[i] == 'I') {
                    res += 1;
                }
                else if (charArray[i] == 'V') {
                    res += 5;
                }
                else if (charArray[i] == 'X') {
                    res += 10;
                }
                else if (charArray[i] == 'L') {
                    res += 50;
                }
                else if (charArray[i] == 'C') {
                    res += 100;
                }
                else if (charArray[i] == 'D') {
                    res += 500;
                }
                else if (charArray[i] == 'M') {
                    res += 1000;
                }
            }
            return res;
        }
    }

    class Solution1 {


        /**
         * 当前位置的元素比下个位置的元素小，就减去当前值，否则加上当前值
         * 输入: s = "MCMXCIV"
         * 输出: 1994
         * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
         * @param s
         * @return
         */
        public int romanToInt(String s) {
            int res = 0;
            int preV = getV(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                int nums = getV(s.charAt(i));
                if (preV < nums) {
                    res -= preV;
                }else {
                    res += preV;
                }
                preV = nums;
            }
            res += preV;
            return res;
        }

        public int getV(char c) {
            switch (c) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
            }
            return 0;
        }
    }
}
