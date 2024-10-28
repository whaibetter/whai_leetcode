package cn.whaifree.LCR;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/25 15:03
 * @注释
 */
public class LCR106 {

    @Test
    public void test() {
        // [[1,2,3],[0,2],[0,1,3],[0,2]]
        int[][] graph = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        // [[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]]
        graph = new int[][]{{}, {2, 4, 6}, {1, 4, 8, 9}, {7, 8}, {1, 2, 8, 9}, {6, 9}, {1, 5, 7, 8, 9}, {3, 6, 9}, {2, 3, 4, 6, 9}, {2, 4, 5, 6, 7, 8}};
        Solution solution = new Solution();
        boolean bipartite = solution.isBipartite(graph);
        System.out.println(bipartite);
    }

    class Solution {
        private final static int UN_SIGN = 0;
        private final static int RED_SIGN = 1;
        private final static int BLACK_SIGN = 2;
        int[] color = null;
        boolean res = true;
        /**
         * 深度优先遍历dfs
         * 如果是没有标记过的，红标记为黑，黑标记为红
         * 如果是标记过的，如果颜色一样，直接返回false
         *
         * @param graph
         * @return
         */
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            color = new int[n];


            // 这个for确保每个连通的图，都遍历一遍
            // [[],[2,4,6],[1,4,8,9],[7,8],[1,2,8,9],[6,9],[1,5,7,8,9],[3,6,9],[2,3,4,6,9],[2,4,5,6,7,8]]
            // 比如这个，第一个0与任何都不连通，就会直接返回true，
            for (int i = 0; i < n && res; i++) {
                if (color[i] == UN_SIGN) {
                    color[i] = RED_SIGN;
                    dfs(graph, i, RED_SIGN);
                }
            }
            return res;
        }

        /**
         *
         * @param graph
         * @param now 现在是拿个节点I
         * @param nowColor 此节点now的颜色
         */
        public void dfs(int[][] graph, int now, int nowColor) {
            int hopeNextColor;
            if (nowColor == RED_SIGN) {
                hopeNextColor = BLACK_SIGN;
            }else {
                hopeNextColor = RED_SIGN;
            }
            int[] targets = graph[now];
            for (int target : targets) {
                if (color[target] == UN_SIGN) {
                    color[target] = hopeNextColor;
                    dfs(graph, target, hopeNextColor);
                    if (!res) { // 一旦有false直接返回，剪枝
                        return;
                    }
                } else if (color[target] != hopeNextColor) {
                    // 本节点已经遍历过了，并且与期望不一样，直接false
                    res = false;
                    return;
                }
            }

        }
    }

    class Solution1 {

        private final static int UN_SIGN = 0;
        private final static int RED_SIGN = 1;
        private final static int BLACK_SIGN = 2;
        int[] color = null;
        boolean res = true;

        /**
         *
         * @param graph
         * @return
         */
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            color = new int[n];
            Arrays.fill(color, UN_SIGN);
            for (int i = 0; i < n; i++) {
                if (color[i] == UN_SIGN) {
                    if (!WFS(graph, i, RED_SIGN)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public boolean WFS(int[][] graph, int now, int nowColor) {
            Deque<Integer> queue = new LinkedList<>();
            queue.add(now);
            color[now] = RED_SIGN;
            while (!queue.isEmpty()) {
                Integer pop = queue.pop();
                int HopeNext = 0;
                if (color[pop] == RED_SIGN) {
                    HopeNext = BLACK_SIGN;
                } else {
                    HopeNext = RED_SIGN;
                }
                int[] Targets = graph[pop];
                for (int target : Targets) {
                    if (color[target] == UN_SIGN) {
                        color[target] = HopeNext;
                        queue.add(target);
                    }else if (color[target] != HopeNext) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

//    class Solution {
//        Set<Integer> set1;
//        Set<Integer> set2;
//        LinkedList<Integer> path;
//        /**
//         * 建立两个Set
//         *
//         * 递归深度遍历
//         * - 如果Set中存在，直接、return
//         * - 如果不存在加入Set
//         *
//         * @param graph
//         * @return
//         */
//        public boolean isBipartite(int[][] graph) {
//            set1 = new HashSet<>();
//            set2 = new HashSet<>();
//            path = new LinkedList<>();
//            path.add(0);
//            boolean dfs = dfs(graph, 0, true);
//            return dfs;
//        }
//
//        public boolean dfs(int[][] graph,int now,boolean toSet1) {
//            if (set1.contains(now) || set2.contains(now)) {
//                return false;
//            }
//            if (toSet1) {
//                set1.add(now);
//            }else {
//                set2.add(now);
//            }
//
//
//            int[] tos = graph[now];
//            for (int i = 0; i < tos.length; i++) {
//                int wantTo = tos[i];
//                // 这个边的两头在同一个集合
//                if (toSet1 && set1.contains(wantTo)) {
//                    continue;
//                }
//                if (!toSet1 && set2.contains(wantTo)) {
//                    continue;
//                }
//                path.add(wantTo);
//                if (dfs(graph, wantTo, !toSet1)) {
//                    return true;
//                }
//
//                path.remove(path.size() - 1);
//            }
//
//            return false;
//        }
//    }
}
