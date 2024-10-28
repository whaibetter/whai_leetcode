package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 10:29
 * @注释
 */
public class LeetCode6 {

    @Test
    public void test() {
        String s = "AB";
        int numRows = 1;
        String res = new Solution().convert(s, numRows);
        System.out.println(res);
    }

    class Solution {
        public String convert(String s, int numRows) {
            if (numRows == 1) {
                return s;
            }
            List<StringBuilder> result = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                result.add(new StringBuilder());
            }


            int index = 0;
            boolean down = true;
            for (int i = 0; i < s.toCharArray().length; i++) {
                result.get(index).append(s.charAt(i));
                if (down) {
                    if (index == numRows - 1) {
                        down = false;
                        index--;
                    }else {
                        index++;
                    }
                }else {
                    if (index == 0) {
                        down = true;
                        index++;
                    }else {
                        index--;
                    }
                }
            }

            StringBuilder res = new StringBuilder();
            for (StringBuilder sb : result) {
                res.append(sb.toString());
            }
            return res.toString();
        }
    }
}
