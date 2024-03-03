package cn.whaifree.redo.redo_24_3_1;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 12:24
 * @注释
 */
public class LeetCode51 {

    @Test
    public void test() {
        new Solution().solveNQueens(4);
    }




    class Solution {

        char[][] map = null;
        List<List<String>> res = new ArrayList<>();

        /**
         * 判断是否符合条件
         * @param n
         * @return
         */
        public List<List<String>> solveNQueens(int n) {
            map = new char[n][n];
            for (char[] chars : map) {
                Arrays.fill(chars, '.');
            }
            backTracking(n, 0);
            return res;
        }

        public void backTracking(int n,int row) {
            if (n == row) {
                List<String> e = new ArrayList<>();
                for (char[] chars : map) {
                    StringBuilder s = new StringBuilder();
                    for (char aChar : chars) {
                        s.append(aChar);
                    }
                    e.add(s.toString());
                }
                res.add(e);
                return;
            }

            for (int i = 0; i < n; i++) {
                if (isValid(i, row, n)) {
                    map[row][i] = 'Q';
                    backTracking(n, row + 1);
                    map[row][i] = '.';
                }
            }

        }

        boolean isValid(int col, int row,int n) {

            // 往上查找
            for (int i = row; i >= 0; i--) {
                if (map[i][col] == 'Q') {
                    return false;
                }
            }
            // 左上45 这里不检查本节点可以提升很大的效率
            for (int i = col-1, j = row-1; i >= 0 && j >= 0; i--, j--) {
                if (map[j][i] == 'Q') {
                    return false;
                }
            }
            // 右上45
            for (int i = col + 1, j = row - 1; i < n && j >= 0; i++, j--) {
                if (map[j][i] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
}
