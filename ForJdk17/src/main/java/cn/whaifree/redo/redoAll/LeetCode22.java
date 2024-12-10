package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/24 2:35
 * @注释
 */
public class LeetCode22 {

    @Test
    public void test() {

        System.out.println(new Solution().generateParenthesis(1));

    }

    class Solution {
        List<String> res;
        StringBuilder path;
        Character[] characters;

        public List<String> generateParenthesis(int n) {

//            characters = new Character[n * 2];
//            for (int i = 0; i < n; i++) {
//                characters[i] = '(';
//            }
//            for (int i = n; i < n * 2; i++) {
//                characters[i] = ')';
//            }

            res = new ArrayList<>();
            path = new StringBuilder();
            circle(n, n);
            return res;
        }

        /**
         * <img src="http://so9vd9h8j.hd-bkt.clouddn.com/efbe574e5e6addcd1c9dc5c13a50c6f162a2b14a95d6aed2c394e18287a067fa-image.png">
         *
         * @param left
         * @param right
         */

        public void circle(int left, int right) {
            if (left == 0 && right == 0) {
                res.add(path.toString());
                return;
            }
            if (left < 0) {
                return;
            }
            if (left > right) {
                return;
            }

            path.append("(");
            circle(left - 1, right);
            path.deleteCharAt(path.length() - 1);

            path.append(")");
            circle(left, right - 1); // 回溯后左边括号还是可以用的
            path.deleteCharAt(path.length() - 1);


        }
    }
}
