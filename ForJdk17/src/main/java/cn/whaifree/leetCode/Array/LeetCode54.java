package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 */
public class LeetCode54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
        List<Integer> s = new ArrayList<>(width * height);

        if (width == 1) {

        } else if (height == 1) {

        }

        int loop = Math.min(width, height) / 2;
        int start = 0;
        int i = 0, j = 0;
        while (start < loop) {

            for (i = start; i < width - start - 1; i++) {
                s.add(matrix[start][i]);
            }

            for (j = start; j < height - start - 1; j++) {
                s.add(matrix[j][i]);
            }

            for (; i >= start + 1; i--) {
                s.add(matrix[j][i]);
            }

            for (; j >= start + 1; j--) {
                s.add(matrix[j][i]);
            }
            start++;
        }

        if (width > height) {
            if (height == 1) {
                // 横向填充
                for (; i < width - start; i++) {
                    s.add(matrix[start][i]);
                }
            }else if (height % 2 == 1) {
                // 横向填充
                for (; i < width - start-1; i++) {
                    s.add(matrix[start][i + 1]);
                }
            }

        } else {
            // 纵向填充
            if (width == 1) {
                for (; j < height - start; j++) {
                    s.add(matrix[j][i]);
                }
            } else if (width % 2 == 1 ) {
                for (; j < height - start - 1; j++) {
                    s.add(matrix[j+1][i + 1]);
                }
            }
        }

        return s;

    }


    /**
     * 时间复杂度：O(mn)O(mn)O(mn)，其中 mmm 和 nnn 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
     *
     * 空间复杂度：O(1)O(1)O(1)。除了输出数组以外，空间复杂度是常数。
     *
     * @param matrix
     * @return
     */

    public List<Integer> spiralOrder1(int[][] matrix) {
        int width = matrix[0].length;
        int height = matrix.length;
        List<Integer> s = new ArrayList<>(width * height);

        if (height == 1) {
            int[] line = matrix[0];
            for (int i : line) {
                s.add(i);
            }
            return s;
        } else if (width == 1) {
            for (int[] ints : matrix) {
                s.add(ints[0]);
            }
            return s;
        }

        int loop = Math.min(width, height) / 2;
        int start = 0;
        int i = 0, j = 0;
        while (start < loop) {

            for (i = start; i < width - start - 1; i++) {
                s.add(matrix[start][i]);
            }

            for (j = start; j < height - start - 1; j++) {
                s.add(matrix[j][i]);
            }

            for (; i >= start + 1; i--) {
                s.add(matrix[j][i]);
            }

            for (; j >= start + 1; j--) {
                s.add(matrix[j][i]);
            }
            start++;
        }

        if (width > height) {
            if (height % 2 == 1) {
                // 横向填充
                for (; i < width - start-1; i++) {
                    s.add(matrix[start][i + 1]);
                }
            }
        } else {
            if (width % 2 == 1) {
                for (; j < height - start - 1; j++) {
                    s.add(matrix[j+1][i + 1]);
                }
            }
        }

        return s;

    }
    @Test
    public void test() {
        spiralOrder1(new int[][]{
                {1,2},
                {1,3},
                {1,3}
        }).forEach(System.out::println);
    }
}
