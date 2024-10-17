package cn.whaifree.leetCode.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/17 16:26
 * @注释
 */
public class Kama99 {

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

        int res = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (!visited[i][j] && input[i][j] == 1) {
                    // 没有走过的节点+为陆地（1）
                    res++;
                    method(input, visited, i, j);
                }
            }
        }
        System.out.println(res);
    }
    public static int method(int[][] input, boolean[][] looking, int x, int y) {
        int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}}; // 表示四个方向

        int res = 0;
        int[] item = new int[]{x, y};
        looking[x][y] = true;
        Deque<int[]> queue = new LinkedList<>();
        queue.add(item);
        while (!queue.isEmpty()) {
            int[] pop = queue.pop();
            int x1 = pop[0];
            int y1 = pop[1];
            for (int i = 0; i < 4; i++) {
                int nextX = x1 + dir[i][0];
                int nextY = y1 + dir[i][1];
                if (nextX >= 0 && nextX < input.length && nextY >= 0 && nextY < input[0].length) {
                    if (!looking[nextX][nextY] && input[nextX][nextY] == 1) { // 只有1才遍历，这样就可以保证只在小岛屿内
                        // （下一次的节点）没有遍历过，并且为1，
                        queue.add(new int[]{nextX, nextY});
                        looking[nextX][nextY] = true; // 进入队列就标志看过了
                    }
                }
            }
        }
        return res;
    }
}
