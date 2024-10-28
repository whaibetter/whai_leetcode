package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/20 12:03
 * @注释
 */
public class LeetCode51 {

    @Test
    public void test() {
        new Solution().solveNQueens(4).forEach(
                list -> {
                    for (String s : list) {
                        System.out.println(s);
                    }

                }
        );
    }


    class Solution {
        List<List<String>> res = new ArrayList<>();

        // 地图
        char[][] chessMap = null;

        /**
         * 1. 形成棋盘
         * 2. 选择点进行填充
         *      填充后，判断该点上列、斜45、45度角是否有被使用过
         *      - 如果有，回溯到上次递归
         *      - 没有，标记该点Q，去下一行row+1进行填充
         * @param n
         * @return
         */
        public List<List<String>> solveNQueens(int n) {
            chessMap = new char[n][n];
            for (char[] c : chessMap) {
                Arrays.fill(c, '.');
            }
            backTracking(n, 0);

            return res;
        }

        public void backTracking(int n, int  row) {
            if (row == n) {
                List<String> element = new ArrayList<>();
                for (char[] chars : chessMap) {
                    StringBuilder s = new StringBuilder();
                    for (char aChar : chars) {
                        s.append(aChar);
                    }
                    element.add(s.toString());
                }
                res.add(element);
                return;
            }

            for (int col = 0; col < n; col++) {
                if (isValid(n, col, row)) {

                    chessMap[row][col] = 'Q';
                    backTracking(n, row + 1);
                    // 回溯
                    chessMap[row][col] = '.';
                }
            }

        }

        /**
         * 判断是否有效
         *
         * @param n   n皇后
         * @param col 列
         * @param row 行
         * @return
         */
        public boolean isValid(int n, int col, int row) {
            // 判断某列是否有被使用过的，其实只要该点往上搜索就可以，下面的必定为.
            for (int i = 0; i < row; i++) {
                if (chessMap[i][col] == 'Q') {
                    return false;
                }
            }
            // 判断45度 该点左上搜索
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                // 只要让某个点，不断往斜45度向左上检查就可以
                if (chessMap[i][j] == 'Q') {
                    return false;
                }
            }
            // 判断斜45、135度 该点右边上搜索
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                // 只要让某个点，不断往斜45度向左上检查就可以
                if (chessMap[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
}
