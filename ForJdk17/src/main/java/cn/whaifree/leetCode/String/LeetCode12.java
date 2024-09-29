package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 21:43
 * @注释
 */
public class LeetCode12 {
    class Solution1 {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        public String intToRoman(int num) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                int v = nums[i];
                String c = romans[i];
                while (num >= v) {
                    num -= v;
                    stringBuilder.append(c);
                }
                if (num == 0) { // 效率
                    break;
                }
            }
            return stringBuilder.toString();
        }
    }
    class Solution {
        static Map<Integer,String> map = new HashMap<>();

        static {
            map.put(1,"I");
            map.put(2,"II");
            map.put(3,"III");
            map.put(4,"IV");
            map.put(5,"V");
            map.put(6,"VI");
            map.put(7,"VII");
            map.put(8,"VIII");
            map.put(9,"IX");
            map.put(10,"X");
            map.put(20,"XX");
            map.put(30,"XXX");
            map.put(40,"XL");
            map.put(50,"L");
            map.put(60,"LX");
            map.put(70,"LXX");
            map.put(80,"LXXX");
            map.put(90,"XC");
            map.put(100,"C");
            map.put(200,"CC");
            map.put(300,"CCC");
            map.put(400,"CD");
            map.put(500,"D");
            map.put(600,"DC");
            map.put(700,"DCC");
            map.put(800,"DCCC");
            map.put(900,"CM");
            map.put(1000,"M");
            map.put(2000,"MM");
            map.put(3000,"MMM");
        }
        public String intToRoman(int num) {


            StringBuilder sb = new StringBuilder();
            String x = String.valueOf(num);
            for (int i = 0; i < x.length(); i++) {
                char c = x.charAt(i);
                if (c == '0') {
                    continue;
                }
                int v = (int) ((c - '0') * Math.pow(10, x.length() - i - 1));
                sb.append(map.get(v));
            }
            return sb.toString();
        }

    }

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.intToRoman(1994));
    }
}
