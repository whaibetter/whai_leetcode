package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/31 11:28
 * @注释
 */
public class LeetCode289 {

    @Test
    public void test() {
        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new Solution().gameOfLife(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
        }
    }

    class Solution {
        /**
         * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；1 2
         * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活； 1 1
         * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；1 2
         * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；0 3
         *
         * 2 之前是活着，现在死了
         * 3 之前是死的，现在活了
         *
         * 最后遍历吧3变成1，把2变成0
         *
         * @param board
         */
        public void gameOfLife(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    int count = aliveCount(board, i, j);
                    if (board[i][j] == 1) { // 之前是活着的
                        if (count < 2) {
                            board[i][j] = 2;
                        }else if (count > 3) {
                            board[i][j] = 2;
                        }
                    }else {
                        if (count == 3) {
                            board[i][j] = 3;
                        }
                    }

                }
            }

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 3) {
                        board[i][j] = 1;
                    } else if (board[i][j] == 2) {
                        board[i][j] = 0;
                    }
                }
            }
        }


        public int aliveCount(int[][] board, int i, int j) {
            int res = 0;
            for (int ti = i - 1; ti < i + 2; ti++) {
                for (int tj = j - 1; tj < j + 2; tj++) {
                    if (ti == i && tj == j) {
                        continue;
                    }
                    if (ti >= 0 && ti < board.length && tj >= 0 && tj < board[0].length) {
                        if (board[ti][tj] == 1 || board[ti][tj] == 2) {
                            res++;
                        }
                    }
                }
            }
            return res;
        }
    }

}
