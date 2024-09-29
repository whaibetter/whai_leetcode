package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LeetCode93 {

    @Test
    public void test() {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
    }


    class Solution {

        List<String> path = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        public List<String> restoreIpAddresses(String s) {
            backTracking(s, 0, 0);
            return ans;
        }

        public void backTracking(String s, int start,int number) {
            if (number > 4 ) {
                return;
            }

            if (start >= s.length() && path.size() == 4) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String string : path) {
                    stringBuilder.append(string).append(".");
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                ans.add(stringBuilder.toString());
            }

            for (int i = start; i < s.length(); i++) {
                if (isValid(s, start, i + 1)) {
                    path.add(s.substring(start, i + 1));
                    backTracking(s, i + 1, number + 1);
                    path.remove(path.size() - 1);
                }
            }

        }

        public boolean isValid(String s, int start, int end) {
            String substring = s.substring(start, end);
            if (substring.length() > 3) {
                return false;
            }


            if (substring.length() >= 2 && substring.startsWith("0")) {
                return false;
            }
            int integer = Integer.parseInt(substring);
            return integer >= 0 && integer <= 255;
        }
    }
}
