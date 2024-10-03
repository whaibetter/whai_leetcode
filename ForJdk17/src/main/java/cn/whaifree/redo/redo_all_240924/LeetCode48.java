package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/30 11:10
 * @注释
 */
public class LeetCode48 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        solution.rotate(matrix);
//        solution.rotate(matrix, 0, 0, 2);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    class Solution {
        public void rotate(int[][] matrix) {

            for (int i = 0; i < matrix.length / 2; i++) {
                for (int j = i; j < matrix.length - 1 - i; j++) {
                    int x = i;
                    int y = j;
                    int n = matrix.length - 1;
                    int tmp = matrix[x][y];
                    matrix[x][y] = matrix[n - y][x];
                    matrix[n - y][x] = matrix[n - x][n - y];
                    matrix[n - x][n - y] = matrix[y][n - x];
                    matrix[y][n - x] = tmp;
                }
            }
        }

        public void rotate(int[][] matrix, int x, int y, int n) {

            int tmp = matrix[x][y];
            matrix[x][y] = matrix[n - y][x];
            matrix[n - y][x] = matrix[n - x][n - y];
            matrix[n - x][n - y] = matrix[y][n - x];
            matrix[y][n - x] = tmp;
        }
    }
}
