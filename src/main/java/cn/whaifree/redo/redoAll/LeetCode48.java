package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/29 0:05
 * @注释
 */
public class LeetCode48 {

    @Test
    public void test() {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new Solution().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    class Solution {
        public void rotate(int[][] matrix) {

            int length = matrix.length;


            for (int i = 0; i < length / 2; i++) {
                for (int j = i; j < length - 1 - i; j++) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[length - j - 1][i];
                    matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
                    matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
                    matrix[j][length - i - 1] = tmp;
                }
            }

        }

        public void swap(int[][] matrix, int i, int j) {
            int tmp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = tmp;
        }
    }
}
