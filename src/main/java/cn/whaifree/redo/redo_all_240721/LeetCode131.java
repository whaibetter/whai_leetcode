package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/1 21:45
 * @注释
 */
public class LeetCode131 {

    public static void main(String[] args) {
        new Solution1().partition("aab").forEach(System.out::println);
    }

    static class Solution {


        /**
         * 分割回文串
         * aab
         * <p>
         * boolean[][] dp
         * <p>
         * dp[i][j]表示i~j是不是回文
         * <p>
         * if dp[i+1][j-1]==true && s[i] == s[j]
         * dp[i][j] == true
         * <p>
         * i从大到小
         * j从小到大
         * <p>
         * <p>
         * 初始化
         * i=j true
         * <p>
         * a a b
         * <p>
         * 0 1 2 3
         * 0 1
         * 1   1 1 0
         * 2     1 0
         * 3       1
         *
         * @param s
         * @return
         */
        public List<List<String>> partition(String s) {

            List<List<String>> res = new ArrayList<>();
            int length = s.length();
            boolean[][] dp = new boolean[length + 1][length + 1];

            Set<String> o = new HashSet<>();

            for (int i = length; i > 0; i--) {
                for (int j = i; j <= length; j++) {
                    if (i == j) {
                        o.add(s.substring(i - 1, j));
                        dp[i][j] = true;
                        continue;
                    }
                    if ((i == j - 1 || dp[i + 1][j - 1]) && s.charAt(i - 1) == s.charAt(j - 1)) {
                        o.add(s.substring(i - 1, j));
                        dp[i][j] = true;
                    }
                }
            }
            o.forEach(o1 -> System.out.println(o1));
//            res.add(o);
            return res;
        }
    }

    static class Solution1 {


        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();

        public List<List<String>> partition(String s) {
            back(s, 0);
            return res;
        }

        public void back(String s, int start) {
            if (start >= s.length()) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = start; i < s.length(); i++) {
                if (isHuiWen(s, start, i)) {
                    path.add(s.substring(start, i + 1));
                    back(s, i + 1);
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


    @Test
    public void test() {
        new Sol().partition("aabaacac");
    }
    static class Sol {

        /**
         * dp[i][j] 表示i-j是否是回文串
         *
         * if s[i]==s[j] && dp[i+1][j-1|
         *      true
         *      i从大到小，j从小到大
         *
         *
         * @param s
         * @return
         */
        public List<List<String>> partition(String s) {
            char[] charArray = s.toCharArray();
            boolean[][] dp = new boolean[charArray.length + 1][charArray.length + 1];
//            for (int i = 0; i < dp.length; i++) {
//                dp[i][i] = true;
//            }

            for (int i = charArray.length; i > 0; i--) {
                for (int j = i; j <= charArray.length; j++) {
                    if (charArray[i - 1] == charArray[j - 1] && (j - i < 2 || dp[i + 1][j - 1])) {
                        dp[i][j] = true;
                    }
                }
            }

            for (int i = 0; i < charArray.length; i++) {
                for (int j = i; j < charArray.length; j++) {
                    if (dp[i][j]) {
                        System.out.println(s.substring(i - 1, j));
                    }
                }
            }
            return null;
        }

        public static void main(String[] args) {
            while (true) {

            }
        }
    }


}
