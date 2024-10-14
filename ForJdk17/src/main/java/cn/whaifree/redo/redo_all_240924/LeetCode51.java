package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/14 14:44
 * @注释
 */
public class LeetCode51 {
    @Test
    public void test() {
        System.out.println(new Solution().solveNQueens(4));
    }


    class Solution {

        boolean[][] map = null;
        List<List<String>> res = new ArrayList<>();

        public List<List<String>> solveNQueens(int n) {
            map = new boolean[n][n];
            back(0);
            return res;
        }


        public void back(int row) {
            if (row == map.length) {
                List<String> path = new ArrayList<>();
                for (int i = 0; i < map.length; i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < map.length; j++) {
                        if (map[i][j]) {
                            sb.append("Q");
                        } else {
                            sb.append(".");
                        }
                    }
                    path.add(sb.toString());
                }
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = 0; i < map.length; i++) {
                if (isValid(row, i)) {
                    map[row][i] = true;
                    back(row + 1);
                    map[row][i] = false;
                }
            }
        }

        public boolean isValid(int x, int y) {
            int n = map.length;
            // 上边
            for (int i = x; i >= 0; i--) {
                if (map[i][y]) {
                    return false;
                }
            }
            // 左
            for (int j = y; j >= 0; j--) {
                if (map[x][j]) {
                    return false;
                }
            }
            // 左上45
            for (int i = 1; i < n; i++) {
                if (x - i >= 0 && y - i >= 0 && map[x - i][y - i]) {
                    return false;
                }
            }
            // 右上
            for (int i = 1; i < n; i++) {
                if (x - i >= 0 && y + i < n && map[x - i][y + i]) {
                    return false;
                }
            }


            return true;
        }
    }


}
