package cn.whaifree.leetCode.Graph;

import org.junit.Test;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/17 18:09
 * @注释
 */
public class LeetCode797 {

    @Test
    public void test() {
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        List<List<Integer>> res = new Solution().allPathsSourceTarget(graph);
        res.forEach(System.out::println);
    }

    class Solution {
        List<List<Integer>> res = null;
        List<Integer> path = null;
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            res = new java.util.ArrayList<>();
            path = new java.util.ArrayList<>();
            path.add(0);
            dfs(graph, 0);
            return res;
        }

        public void dfs(int[][] graph, int cur) {
            if (!path.isEmpty() && graph.length - 1 == path.get(path.size() - 1)) {
                res.add(new java.util.ArrayList<>(path));
                return;
            }

            int[] ints = graph[cur];
            for (int i = 0; i < ints.length; i++) {
                path.add(ints[i]);
                dfs(graph, ints[i]); // 0-4
                path.remove(path.size() - 1);
            }
        }
    }
}
