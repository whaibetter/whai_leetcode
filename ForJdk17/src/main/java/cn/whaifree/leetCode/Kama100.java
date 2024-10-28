package cn.whaifree.leetCode.Graph;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/23 11:29
 * @注释
 */
public class Kama100 {


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

        int max = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (!visited[i][j] && input[i][j] == 1) {
                    visited[i][j] = true;
                    int in = in(input, visited, i, j) + 1; // 注意这里也是一小块陆地
                    max = Math.max(max, in);
                }
            }
        }
        System.out.println(max);


    }

    static int[][] direct = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 广度有限 用队列
     * - 深入搜索 用地柜
     * @param input
     * @param visited
     * @param x
     * @param y
     */
    public static int in(int[][] input, boolean[][] visited, int x, int y) {
        int res = 0;
        for (int i = 0; i < direct.length; i++) {
            int nx = x + direct[i][0];
            int ny = y + direct[i][1];
            if (nx >= 0 && nx < input.length && ny >= 0 && ny < input[0].length
                    && !visited[nx][ny]) {
                if (input[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    res += 1;
                    res += in(input, visited, nx, ny);
                }
            }
        }
        return res;
    }
}
