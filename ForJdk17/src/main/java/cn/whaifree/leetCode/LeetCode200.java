package cn.whaifree.leetCode;

import org.junit.Test;

import java.security.PublicKey;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/4 11:27
 * @注释
 */
public class LeetCode200 {

    @Test
    public void test() {
        char[][] grid = {{'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        Solution solution = new Solution();
        int res = solution.numIslands(grid);
        System.out.println(res);
    }

    class Solution {
        public int numIslands(char[][] grid) {
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            int res = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    // 没遍历过的陆地
                    if (grid[i][j] == '1' && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(grid, visited, i, j);
                        res++;
                    }
                }
            }
            return res;
        }


        int[][] direct = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public void dfs(char[] [] grid, boolean[][] visited, int i, int j) {
            for (int x = 0; x < direct.length; x++) {
                int nextX = i + direct[x][0];
                int nextY = j + direct[x][1];
                if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length) {
                    if (grid[nextX][nextY] == '1' && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        dfs(grid, visited, nextX, nextY);
                    }
                }
            }
        }

        public void wfs(char[] [] grid, boolean[][] visited, int i, int j) {


        }


    }

}
