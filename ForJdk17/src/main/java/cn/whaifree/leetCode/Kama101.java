package cn.whaifree.leetCode.Graph;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/23 12:00
 * @注释
 */
public class Kama101 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int[][] input = new int[a][b];
        boolean[][] visited = new boolean[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                input[i][j] = scanner.nextInt();
            }
        }

        // 在边缘处进入，一旦为1才进入，边缘陆地全部进入
        for (int i = 0; i < a; i++) {
            if (input[i][0] == 1) {
                in(input, i, 0);
            }
            if (input[i][b - 1] == 1) {
                in(input, i, b - 1);
            }
        }

        for (int j = 0; j < b; j++) {
            if (input[0][j] == 1) {
                in(input, 0, j);
            }
            if (input[a - 1][j] == 1) {
                in(input, a - 1, j);
            }
        }
        res = 0;// 重新开始计数
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (input[i][j] == 1) {
                    in(input, i, j);
                }
            }
        }
        System.out.println(res);
    }

    static int res = 0;
    static int[][] direct = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 广度有限 用队列
     * - 深入搜索 用地柜
     *
     *
     * 孤岛 所有单元格都不接触边缘的岛屿
     *
     * 从周边找到陆地然后 通过 dfs或者bfs
     * 将周边靠陆地且相邻的陆地都变成海洋，
     * 然后再去重新遍历地图 统计此时还剩下的陆地就可以了。
     * @param input

     * @param x
     * @param y
     */
    public static void in(int[][] input, int x, int y) {
        input[x][y] = 0;
        res++;
        for (int i = 0; i < direct.length; i++) {
            int nx = x + direct[i][0];
            int ny = y + direct[i][1];
            if (nx >= 0 && nx < input.length && ny >= 0 && ny < input[0].length) {
                if (input[nx][ny] == 1) {
                    in(input, nx, ny);
                }
            }
        }
    }
}