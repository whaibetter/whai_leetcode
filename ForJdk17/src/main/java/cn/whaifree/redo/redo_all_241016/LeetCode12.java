package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/16 12:10
 * @注释
 */
public class LeetCode12 {
    @Test
    public void test() {

        int num = 1994;
        String result = new Solution().intToRoman(num);
        System.out.println(result);
    }

    class Solution {
        // 3999
        static int[] nums = new int[]{1000, 900, 500, 400, 100, 90,50, 40, 10, 9, 5, 4, 1};
        static String[] strs = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            while (num != 0) {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] <= num) {
                        sb.append(strs[i]);
                        num -= nums[i];
                        break;
                    }
                }
            }
            return sb.toString();
        }
    }

}
