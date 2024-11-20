package cn.whaifree.leetCode;

import org.junit.Test;

import java.security.PublicKey;
import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/18 21:48
 * @注释
 */
public class LeetCode994 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        System.out.println(solution.orangesRotting(grid));

    }

    class Solution {


        public int[][] direct = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        /**
         * 深度优先遍历
         * - 如果是腐烂的，全部进入队列
         * - 每次获取队列大小，队列内为本time内的腐烂，
         * - 把队列内的周围的全部放入
         *
         * 统计新鲜橘子的数量，如果数量最后还是?>0，则表示无法全部腐烂
         *
         * @param grid
         * @return
         */
        public int orangesRotting(int[][] grid) {
            int fresh = 0;
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                    }else if (grid[i][j] == 1) {
                        fresh++;
                    }
                }
            }
            int res = 0;
            while (!queue.isEmpty() && fresh > 0) {
                res++; // 表示一层
                // 拿出所有腐烂的橘子
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] poll = queue.poll();
                    for (int dir = 0; dir < direct.length; dir++) {
                        int x = poll[0] + direct[dir][0];
                        int y = poll[1] + direct[dir][1];
                        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length ) {
                            if (grid[x][y] == 1) {
                                // 如果是新鲜的
                                grid[x][y] = 2;
                                fresh--;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }
                }
            }
            return fresh == 0 ? res : -1;
        }




    }
}
