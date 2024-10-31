package cn.whaifree.leetCode;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/28 15:23
 * @注释
 */
public class LeetCode43 {

    @Test
    public void test() {
        String num1 = "12";
        String num2 = "12";
        Solution solution = new Solution();
        String result = solution.multiply(num1, num2);
        System.out.println(result);
    }

    class Solution {
        /**
         * 12345
         *   678
         *
         *   <img src="https://assets.leetcode-cn.com/solution-static/43/sol1.png">
         * @param num1
         * @param num2
         * @return
         */
        public String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            char[] char1 = num1.toCharArray();
            char[] char2 = num2.toCharArray();

            int[] ans = new int[char1.length + char2.length];

            for (int i = char1.length - 1; i >= 0; i--) {
                int V1 = char1[i] - '0';
                for (int j = char2.length - 1; j >= 0; j--) {
                    int V2 = char2[j] - '0';
                    int product = V1 * V2;
                    int sum = ans[i + j + 1] + product;
                    int pre = sum / 10;
                    int retail = sum % 10;
                    ans[i + j + 1] = retail;
                    ans[i + j] = ans[i + j] + pre;
                }
            }

            StringBuilder sb=new StringBuilder();
            for (int i = 0; i < ans.length; i++) {
                if (i == 0 && ans[i] == 0) {
                    continue;
                }
                sb.append(ans[i]);
            }
            return sb.toString();
        }
    }

}
