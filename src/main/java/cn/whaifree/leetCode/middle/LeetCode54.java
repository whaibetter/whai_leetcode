package cn.whaifree.leetCode.middle;

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

    @Test
    public void test() {
        spiralOrder(new int[][]{
                {1,2,4},
                {5,6,8},
                {9,10,12}

        }).forEach(System.out::println);
    }
}
