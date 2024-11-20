package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/19 16:56
 * @注释
 */
public class LeetCode79 {



    @Test
    public void test() {
//        char[][] board = new char[][]{
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };
//        String word = "ABCCED";
//        System.out.println(new Solution().exist(board, word));

        /**
         * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
输出：false
         */
        char[][] board2 = new char[][]{
                // [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'},
                {'A', 'A', 'A', 'A'}
        };
        String word2 = "B";
        System.out.println(new Solution().exist(board2, word2));
    }

    class Solution1 {

        List<int[]> path = new ArrayList<>();
        int nowIndex = 0;
        public int[][] direct = new int[][]{
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    nowIndex++;
                    path.add(new int[]{i, j});
                    if (back(board, i, j, word, new int[]{i, j})) {
                        return true;
                    }
                    path.remove(path.size() - 1);
                    nowIndex--;
                }
            }
            return false;
        }

        public boolean back(char[][] board, int x, int y, String word, int[] before) {
            if (nowIndex == word.length()) {
                for (int i = 0; i < path.size(); i++) {
                    System.out.println(Arrays.toString(path.get(i)));
                }
                return true;
            }
            char nextExpect = word.charAt(nowIndex);
            for (int i = 0; i < direct.length; i++) {
                int nextX = x + direct[i][0];
                int nextY = y + direct[i][1];
                if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length) {
                    continue;
                }
                if (nextX == before[0] && nextY == before[1]) {
                    continue;
                }
                if (board[nextX][nextY] == nextExpect) {
                    nowIndex++;
                    path.add(new int[]{nextX, nextY});

                    if (back(board, nextX, nextY, word, new int[]{x, y})) {
                        return true;
                    }
                    path.remove(path.size() - 1);
                    nowIndex--;
                }
            }
            return false;
        }
    }

    class Solution {

        int nowIndex = 0;
        public int[][] direct = new int[][]{
                {0, 1},
                {0, -1},
                {1, 0},
                {-1, 0}
        };

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] != word.charAt(0)) {
                        continue;
                    }
                    nowIndex++;
                    char tmp = board[i][j];
                    board[i][j] = '0';
                    if (back(board, i, j, word)) {
                        return true;
                    }
                    board[i][j] = tmp;
                    nowIndex--;
                }
            }
            return false;
        }

        public boolean back(char[][] board, int x, int y, String word) {
            if (nowIndex == word.length()) {
                return true;
            }
            char nextExpect = word.charAt(nowIndex);
            for (int i = 0; i < direct.length; i++) {
                int nextX = x + direct[i][0];
                int nextY = y + direct[i][1];
                if (nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length) {
                    continue;
                }
                if (board[nextX][nextY] == '0') {
                    continue;
                }
                if (board[nextX][nextY] == nextExpect) {
                    char tmp = board[nextX][nextY];
                    board[nextX][nextY] = '0';
                    nowIndex++;
                    if (back(board, nextX, nextY, word)) {
                        return true;
                    }
                    board[nextX][nextY] = tmp;
                    nowIndex--;
                }
            }
            return false;
        }
    }

}
