package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/23 9:18
 * @注释
 */
public class LeetCode73 {

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        new Solution().setZeroes(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }


    class Solution {
        public void setZeroes(int[][] matrix) {

            int yLen = matrix.length;
            boolean[] row = new boolean[yLen];
            int xLen = matrix[0].length;
            boolean[] col = new boolean[xLen];


            for (int y = 0; y < yLen; y++) {
                for (int x = 0; x < xLen; x++) {
                    if (matrix[y][x] == 0) {
                        row[y] = true;
                        col[x] = true;
                    }
                }
            }

            for (int y = 0; y < yLen; y++) {
                for (int x = 0; x < xLen; x++) {
                    if (row[y] || col[x]) {
                        matrix[y][x] = 0;
                    }
                }
            }
        }
    }
}
