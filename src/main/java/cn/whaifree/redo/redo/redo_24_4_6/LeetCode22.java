package cn.whaifree.redo.redo.redo_24_4_6;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/7 12:26
 * @注释
 */
public class LeetCode22 {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<String> result = solution.generateParenthesis(3);
        System.out.println(result);
    }

    class Solution {

        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            StringBuilder x = new StringBuilder();
            backTracking(n, n, n, result, x);
            return result;
        }

        /**
         *
         * @param n
         * @param left 左边可用括号
         * @param right 右边可用括号
         * @param result
         * @param path
         */
        public void backTracking(int n,int left,int right, List<String> result, StringBuilder path) {

            if (left == 0 && right == 0) {
                result.add(path.toString());
                return;
            }
            if (left < 0 || left > right) {
                return;
            }

            path.append("(");
            backTracking(n, left - 1, right, result, path);
            path.deleteCharAt(path.length() - 1);

            path.append(")");
            backTracking(n, left, right - 1, result, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
