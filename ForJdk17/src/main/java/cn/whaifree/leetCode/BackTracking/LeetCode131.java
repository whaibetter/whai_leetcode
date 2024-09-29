package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/10 15:12
 * @注释
 */
public class LeetCode131 {

    @Test
    public void test() {
        String abbc = "aaab";
        new Solution().partition(abbc).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {

        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        public List<List<String>> partition(String s) {
            backTracking(s, 0);
            return res;
        }

        /**
         * 1. 判断回文字符串函数 <br>
         * 2. 从start开始切割，到i i++<br>
         * 递归
         * <img src="https://code-thinking.cdn.bcebos.com/pics/131.%E5%88%86%E5%89%B2%E5%9B%9E%E6%96%87%E4%B8%B2.jpg">
         *
         * @param s
         * @param start
         */
        public void backTracking(String s, int start) {
            if (start >= s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i <  s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    String substring = s.substring(start, i + 1);
                    path.add(substring);
                    backTracking(s, i + 1);
                    path.remove(path.size() - 1);
                }
            }

        }

        //判断是否是回文串
        private boolean isPalindrome(String s, int startIndex, int end) {
            for (int i = startIndex, j = end; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        }
    }
}
