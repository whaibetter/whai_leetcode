package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 14:53
 * @注释
 */
public class LeetCode36 {

    @Test
    public void test() {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new Solution().isValidSudoku(board));
    }

    class Solution1 {
        public boolean isValidSudoku(char[][] board) {
            //直接用数组记录每个行、列、九宫格内数字出现的次数
            int[][] row = new int[9][9];
            int[][] col = new int[9][9];
            int[][][] sub = new int[3][3][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        int numIndex = c - '0' - 1;
                        row[i][numIndex]++;
                        col[j][numIndex]++;
                        sub[i / 3][j / 3][numIndex]++;
                        if (row[i][numIndex] > 1 || col[j][numIndex] > 1 || sub[i / 3][j / 3][numIndex] > 1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    class Solution {
        public boolean isValidSudoku(char[][] board) {


            int length = board.length;
            for (int i = 0; i < length; i++) {
                HashSet<Character> set = new HashSet<>();
                for (int j = 0; j < length; j++) {
                    char c = board[i][j];
                    if (set.contains(c)) {
                        return false;
                    }
                    if (c != '.') {
                        set.add(c);
                    }
                }
            }

            for (int i = 0; i < length; i++) {
                HashSet<Character> set = new HashSet<>();
                for (int j = 0; j < length; j++) {
                    char c = board[j][i];
                    if (set.contains(c)) {
                        return false;
                    }
                    if (c != '.') {
                        set.add(c);
                    }
                }
            }


            for (int i = 0; i < length; i += 3) {
                for (int j = 0; j < length; j += 3) {
                    HashSet<Character> set = new HashSet<>();
                    int x = i;
                    int y = j;
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            int newX = x + k;
                            int newY = y + l;
                            char c = board[newX][newY];
                            if (set.contains(c)) {
                                return false;
                            }
                            if (c != '.') {
                                set.add(c);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
