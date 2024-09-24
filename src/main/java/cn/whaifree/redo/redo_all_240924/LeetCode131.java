package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 12:46
 * @注释
 */
public class LeetCode131 {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<List<String>> lists = solution.partition("aab");
        System.out.println(lists);
    }


    class Solution {


        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        public List<List<String>> partition(String s) {
            backTracking(s, 0);
            return res;
        }

        public void backTracking(String s, int start) {
            if (start >= s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < s.length(); i++) {
                if (isHuiWen(s, start, i)) {
                    path.add(s.substring(start, i + 1));
                    backTracking(s, i + 1);
                    path.remove(path.size() - 1);
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
