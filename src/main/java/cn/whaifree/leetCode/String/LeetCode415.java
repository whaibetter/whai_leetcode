package cn.whaifree.leetCode.String;

import org.junit.Test;

public class LeetCode415 {

    @Test
    public void main() {
        System.out.println(new Solution().addStrings("111", "59999"));
    }


    class Solution {
        public String addStrings(String num1, String num2) {
            char[] c1 = num1.toCharArray();
            char[] c2 = num2.toCharArray();
            int index1 = c1.length - 1;
            int index2 = c2.length - 1;
            StringBuilder stringBuilder = new StringBuilder();
            boolean retail = false;
            while (index1 >= 0 && index2 >= 0) {
                int char1 = c1[index1] - '0';
                int char2 = c2[index2] - '0';
                int tmpAns = char1 + char2;
                if (retail) {
                    tmpAns += 1;
                    retail = false;
                }
                if (tmpAns >= 10) {
                    tmpAns %= 10;
                    retail = true;
                }
                stringBuilder.append(tmpAns);
                index2--;
                index1--;
            }

            while (index1 >= 0) {
                int tmpAns = c1[index1--] - '0';
                if (retail) {
                    tmpAns += 1;
                    retail = false;
                }
                if (tmpAns >= 10) {
                    tmpAns %= 10;
                    retail = true;
                }
                stringBuilder.append( tmpAns);
            }

            while (index2 >= 0) {
                int tmpAns = c2[index2--] - '0';
                if (retail) {
                    tmpAns += 1;
                    retail = false;
                }
                if (tmpAns >= 10) {
                    tmpAns %= 10;
                    retail = true;
                }
                stringBuilder.append(tmpAns);
            }

            if (retail) {
                stringBuilder.append(1);
            }
            return stringBuilder.reverse().toString();
        }
    }
}