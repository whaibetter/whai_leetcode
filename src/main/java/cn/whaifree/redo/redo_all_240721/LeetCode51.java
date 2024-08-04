package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/3 13:34
 * @注释
 */
public class LeetCode51 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        int n = 4;
        System.out.println(solution.solveNQueens(n));
    }

    class Solution {

        char[][] map;
        List<List<String>> res = new ArrayList<>();

        /**
         *
         * @param col 确定下来，下次递归
         * @param n
         */
        public void back(int col, int n) {
            if (col == n) {
                List<String> path = new ArrayList<>();
                for (char[] chars : map) {
                    String s = new String(chars);
                    path.add(s);
                }
                res.add(path);
                return;
            }

            for (int i = 0; i < n; i++) {
                if (isValid(col, i, n)) {
                    map[i][col] = 'Q';
                    back(col + 1, n);
                    map[i][col] = '.';
                }
            }
        }
        public List<List<String>> solveNQueens(int n) {
            map = new char[n][n];
            for (char[] chars : map) {
                Arrays.fill(chars, '.');
            }
            back(0, n);
            return res;
        }

        boolean isValid(int col, int row, int n) {

            // 往左查找
            for (int i = 0; i < col; i++) {
                if (map[row][i] == 'Q') {
                    return false;
                }
            }
            // 左上45
            for (int i = col, j = row; i >= 0 && j >= 0; i--, j--) {
                if (map[j][i] == 'Q') {
                    return false;
                }
            }
            // 左下45
            for (int i = col, j = row; i >= 0 && j < n; i--, j++) {
                if (map[j][i] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
}
