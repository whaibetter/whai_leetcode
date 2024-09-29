package cn.whaifree.leetCode.String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 22:25
 * @注释
 */
public class LeetCode6 {

    @Test
    public void test() {
        Solution solution = new Solution();
        String s = solution.convert("AB", 1);
        System.out.println(s);
    }

    class Solution {
        /**
         * 4
         *
         * 1234321234321
         * @param s
         * @param numRows
         * @return
         */
        public String convert(String s, int numRows) {
            if (numRows == 1) {
                return s;
            }

            List<StringBuilder> lines = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                lines.add(new StringBuilder());
            }


            int i = 0, flag = -1;
            for(char c : s.toCharArray()) {
                lines.get(i).append(c);
                if(i == 0 || i == numRows -1) flag = - flag;
                i += flag;
            }

            StringBuilder res = new StringBuilder();
            for (StringBuilder row : lines) {
                res.append(row);
            }

            return res.toString();



        }
    }

    class Solution1 {
        /**
         * 4
         *
         * 1234321234321
         * @param s
         * @param numRows
         * @return
         */
        public String convert(String s, int numRows) {
            if (numRows == 1) {
                return s;
            }
            int[] nums = new int[s.length()];
            boolean up = true;
            int index = 1;
            for (int i = 0; i < nums.length; i++) {
                if (index == numRows|| index == 1) {
                    up = !up;
                }
                nums[i] = index;

                if (up) {
                    index--;
                }else {
                    index++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= numRows; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (nums[j] == i) {
                        sb.append(s.charAt(j));
                    }
                }
            }
            return sb.toString();
        }
    }
}
