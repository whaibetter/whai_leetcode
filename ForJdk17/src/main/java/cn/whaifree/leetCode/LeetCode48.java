package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/15 12:00
 * @注释
 */
public class LeetCode48 {

    @Test
    public void test()
    {
        // [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
        int[][] matrix = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        Solution solution = new Solution();
        solution.rotate(matrix);
    }

    class Solution {
        public void rotate(int[][] matrix) {
            // 1 1  1 3
            // 1 2  2 3
            // 1 3  3 3

            // 1 1  1 4
            // 1 2  2 4
            // 1 3  3 4
            // 1 4  4 4

            // 1 1  1 4
            // 1 4  4 4
            // 4 4  4 1
            // 4 1  1 1

            // 1 2  2 1
            // 2 1  2 3
            // 2 3  3 2
            // 3 2  2 1
            // 2 1  1 2

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

//            int i = 0;
//            for (int j = i; j < length - 1; j++) {
//                int tmp = matrix[i][j];
//                matrix[i][j] = matrix[length - j - 1][i];
//                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
//                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
//                matrix[j][length - i - 1] = tmp;
//            }
//
//            i = 1;
//            for (int j = i; j < length - 1 - i; j++) {
//                int tmp = matrix[i][j];
//                matrix[i][j] = matrix[length - j - 1][i];
//                matrix[length - j - 1][i] = matrix[length - i - 1][length - j - 1];
//                matrix[length - i - 1][length - j - 1] = matrix[j][length - i - 1];
//                matrix[j][length - i - 1] = tmp;
//            }







            for (int k = 0; k < length; k++) {
                for (int l = 0; l < length; l++) {
                    System.out.print(matrix[k][l] + " ");
                }
                System.out.println();
            }

        }
    }
}
