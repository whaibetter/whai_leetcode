package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/4 13:44
 * @注释
 */
public class LeetCode22 {

    @Test
    public void test() {

        List<String> res =new Solution1().generateParenthesis(3);
        System.out.println(res);
    }

    class Solution {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        /**
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {
            backTracking(n, 0, 0);
            return res;
        }

        /**
         *
         * @param n
         * @param leftCount 左括号用了几个
         * @param rightCount 右括号用了几个
         */
        public void backTracking(int n, int leftCount , int rightCount) {

            if (leftCount > n || rightCount > leftCount) {
                return;
            }

            if (path.length() == n * 2) {
                res.add(path.toString());
                return;
            }


            path.append("(");
            backTracking(n, leftCount + 1, rightCount);
            path.deleteCharAt(path.length() - 1); // 回溯

            path.append(")");
            backTracking(n, leftCount, rightCount + 1);
            path.deleteCharAt(path.length() - 1); // 回溯

        }


    }

    class Solution1 {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        /**
         *
         * @param n
         * @return
         */
        public List<String> generateParenthesis(int n) {
            backTracking(n, n, n);
            return res;
        }

        /**
         *
         * @param n
         * @param leftCount 左括号可用数
         * @param rightCount 右括号可用数
         */
        public void backTracking(int n, int leftCount , int rightCount) {

            if (leftCount == 0 && rightCount == 0) {
                res.add(path.toString());
                return;
            }

            if (leftCount > rightCount) {
                return;
            }


            if (leftCount > 0) {
                path.append("(");
                backTracking(n, leftCount - 1, rightCount);
                path.deleteCharAt(path.length() - 1);
            }
            if (rightCount > 0) {
                path.append(")");
                backTracking(n, leftCount, rightCount - 1);
                path.deleteCharAt(path.length() - 1);
            }

        }


    }

}
