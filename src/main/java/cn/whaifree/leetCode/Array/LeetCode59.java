package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 */
public class LeetCode59 {

    public int[][] generateMatrix(int n) {
        int cycle = 0; // 循环次数
        int start = 0; //  本次循环的开始
        int[][] ints = new int[n][n];
        int fillNumber = 1;
        int i, j;
        while (cycle++ < n / 2) {
            // 每个边填充数字
            int path = n - cycle;
            for (i = start; i < path; i++) {
                ints[start][i]=fillNumber++;
            }
            for (j = start; j < path; j++) {
                ints[j][i] = fillNumber++;
            }
            for (; i >= n - path; i--) {
                ints[j][i] = fillNumber++;
            }
            for (; j >= n - path; j--) {
                ints[j][i]=fillNumber++;
            }
            start++;
        }
        if (n % 2 == 1) {
            ints[start][start] = fillNumber++;
        }
        return ints;
    }

    @Test
    public void test() {
        int[][] ints = generateMatrix(11);
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i+" ");
            }
            System.out.println();
        }

       /* [[1,2,3,4,5,6,7],
        [24,25,26,27,28,29,8],
        [23,40,41,42,43,30,9],
        [22,39,48,49,44,31,10],
        [21,38,47,46,45,32,11],
        [20,37,36,35,34,33,12],
        [19,18,17,16,15,14,13]]*/
    }
}
