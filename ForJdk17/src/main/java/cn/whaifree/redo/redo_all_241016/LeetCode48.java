package cn.whaifree.redo.redo_all_241016;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/6 13:31
 * @注释
 */
public class LeetCode48 {

    class Solution {
        public void rotate(int[][] matrix) {
            int len = matrix.length;
            for (int i = 0; i < len / 2; i++) {
                for (int j = i; j < len; j++) {
                    int x = i;
                    int y = j;
                    int n = len - 1;
                    int tmp = matrix[x][y];
                    matrix[x][y] = matrix[n - y][x];
                    matrix[n - y][x] = matrix[n - x][n - y];
                    matrix[n - x][n - y] = matrix[y][n - x];
                    matrix[y][n - x] = tmp;
                }
            }
        }
    }
}
