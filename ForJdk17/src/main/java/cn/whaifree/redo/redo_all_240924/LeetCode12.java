package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 11:12
 * @注释
 */
public class LeetCode12 {
    @Test
    public void test(){
        Solution solution = new Solution();
        String s = solution.intToRoman(1994);
        System.out.println(s);
    }


    class Solution{
        // 每个标准-前一个 9 5 4 1
        int[] nums = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] romans = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        public String intToRoman(int num) {
            StringBuilder sb = new StringBuilder();
            while (num > 0) {
                for (int i = 0; i < nums.length; i++) {
                    if (num >= nums[i]) {
                        sb.append(romans[i]);
                        num -= nums[i];
                        break;
                    }
                }
                if (num == 0) break;
            }
            return sb.toString();
        }

    }
}
