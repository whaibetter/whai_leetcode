package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 11:03
 * @注释
 */
public class LeetCode48 {
    @Test
    public void test() {
        int[][] matrix  = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        new Solution().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

    }
    class Solution {
        public void rotate(int[][] matrix) {

            int n = matrix.length;
            // i 表示外面层数
            // j 表示本层第几个
            for (int i = 0; i < n / 2; i++) {
                // 注意边的大小 n-i-1
                for (int j = i; j < n - i - 1; j++) {
                    /**
                     * 0 0 i j
                     * 0 3 i n-j
                     * 3 0 n-j i
                     * 3 3 n-i n-j
                     */
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[n - j - 1][i];
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = tmp;
                }
                AtomicInteger atomicInteger = new AtomicInteger(1);
                int j = atomicInteger.incrementAndGet();

            }
        }
    }
}
