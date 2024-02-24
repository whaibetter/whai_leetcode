package cn.whaifree.redo.redo_24_2_22;

import org.junit.Test;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/23 11:56
 * @注释
 */
public class LeetCode131 {

    @Test
    public void test() {
        new Solution().partition("aab").forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }


    class Solution {
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> path = new LinkedList<>();
        public List<List<String>> partition(String s) {
            backTracking(s, 0, s.length() - 1);
            return res;
        }

        public void backTracking(String s, int start, int end) {

            if (start >= s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < s.length(); i++) {
                if (isHuiWen(s, start, i)) {
                    path.add(s.substring(start, i + 1));
                    backTracking(s, i + 1, end);
                    path.removeLast();
                }
            }

        }

        public boolean isHuiWen(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }
}
