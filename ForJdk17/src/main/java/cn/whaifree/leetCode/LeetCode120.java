package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/19 22:43
 * @注释
 */
public class LeetCode120 {
    @Test
    public void test() {
        Solution1 solution = new Solution1();
        System.out.println(solution.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))));
    }

    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            return in(triangle, 0, 0);
        }

        public int in(List<List<Integer>> triangle, int level, int col) {
            if (level == triangle.size()) {
                return 0;
            }
            int now = triangle.get(level).get(col);
            int left = in(triangle, level + 1, col);
            int right = in(triangle, level + 1, col + 1);
            if (left < right) {
                return left + now;
            } else {
                return right + now;
            }
        }
    }

    class Solution1 {
        /**
         * dp[i][j] 表示从0 0到i j的最小路径和
         *
         * dp[i][j] = min(dp[i-1][j-1] dp[i-1][j]) + value[i][j]
         *
         *     2
              3 4
             6 5 7    最左边的只能由右上角推出 最右边只能由左上角推出 6<--3  7<--4
            4 1 8 3
         * @param triangle
         * @return
         */
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] dp = new int[n][n];
            dp[0][0] = triangle.get(0).get(0);
            for (int i = 1; i < n; i++) {
                // 从第一层开始
                dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0); // 每一层第一个元素只有一个来源
                // 遍历第一层除了第一个以外所有节点
                for (int j = 1; j < i; j++) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
                }
                // 还要考虑一行最后一个节点,只有一个来源
                dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
            }

            // 获取到n-1层的所有价值，取最小值
            int min = dp[n - 1][0];
            for (int i = 1; i < n; i++) {
                min = Math.min(min, dp[n - 1][i]);
            }
            return min;
        }
    }
}
