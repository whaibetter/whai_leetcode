package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/28 15:29
 * @注释
 */
public class LeetCode22 {

    @Test
    public void test() {
        Solution solution = new Solution();
        List<String> res = solution.generateParenthesis(3);
        System.out.println(res);
    }
    class Solution {
        List<String> re = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        public List<String> generateParenthesis(int n) {
            backTracking(n, n);
            return re;
        }

        public void backTracking(int left, int right) {
            if (left > right) {
                return;
            }
            if (left < 0 || right < 0) {
                return;
            }
            if (left == 0 && right == 0) {
                re.add(str.toString());
                return;
            }
            str.append("(");
            backTracking(left - 1, right);
            str.deleteCharAt(str.length() - 1);

            str.append(")");
            backTracking(left, right - 1);
            str.deleteCharAt(str.length() - 1);

        }
    }
}
