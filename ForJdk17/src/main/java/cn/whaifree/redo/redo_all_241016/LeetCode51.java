package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/8 18:34
 * @注释
 */
public class LeetCode51 {

    @Test
    public void test() {
        for (List<String> solveNQueen : new Solution().solveNQueens(4)) {
            System.out.println(solveNQueen);
        }
    }
    class Solution {
        List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            char[][] map = new char[n][n];
            for (char[] chars : map) {
                Arrays.fill(chars, '.');
            }
            backTracking(map, 0);
            return res;
        }

        public void backTracking(char[][] map, int x) {
            if (x == map.length) {
                ArrayList<String> e = new ArrayList<>();
                for (char[] chars : map) {
                    e.add(new String(chars));
                }
                res.add(e);
                return;
            }
            if (x > map.length) {
                return;
            }
            for (int i = 0; i < map.length; i++) {
                if (cal(map, x, i)) {
                    map[x][i] = 'Q';
                    backTracking(map, x + 1);
                    map[x][i] = '.';
                }
            }
        }

        // 往左上搜索
        public boolean cal(char[][] map, int x, int y) {
            // 往左边搜索
            for (int i = y; i >= 0; i--) {
                if (map[x][i] == 'Q') {
                    return false;
                }
            }
            // 往上搜索
            for (int i = x; i >= 0; i--) {
                if (map[i][y] == 'Q') {
                    return false;
                }
            }

            // 往左上搜索
            for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
                if (map[i][j] == 'Q') {
                    return false;
                }
            }
            // 往右上搜索
            for (int i = x, j = y; i >= 0 && j < map.length; i--, j++) {
                if (map[i][j] == 'Q') {
                    return false;
                }
            }

            return true;
        }
    }
}
