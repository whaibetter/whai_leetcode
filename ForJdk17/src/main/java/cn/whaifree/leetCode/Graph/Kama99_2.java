package cn.whaifree.leetCode.Graph;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 16:44
 * @注释
 */
public class Kama99_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] grid = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    res++;
                    visited[i][j] = true;
                    depth(grid, visited, i, j);
                }
            }
        }
        System.out.println(res);
    }

    static int[][] direct = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void depth(int[][] map, boolean[][] visited, int x, int y) {

        // 深度优先
        for (int i = 0; i < 4; i++) {
            int[] ints = direct[i];
            int nextX = x + ints[0];
            int nextY = y + ints[1];
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) {
                continue;
            }
            if (visited[nextX][nextY]) { // 访问过的不再访问
                continue;
            }
            if (map[nextX][nextY] == 1) {
                visited[nextX][nextY] = true;
                depth(map, visited, nextX, nextY);
            }
        }
    }




}
