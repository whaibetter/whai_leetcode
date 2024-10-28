package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/27 17:56
 * @注释
 */
public class LeetCode210 {
    @Test
    public void test() {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        Solution solution = new Solution();
        int[] result = solution.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(result));
    }

    class Solution {
        /**
         * pre[i][j] j->i
         *
         * @param numCourses
         * @param prerequisites
         * @return
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 统计每个节点所有 起始的边
            int[] inGre = new int[numCourses];
            Map<Integer, List<Integer>> outLine  = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                int in = prerequisite[1]; // end
                if (!outLine.containsKey(in)) {
                    outLine.put(in, new ArrayList<>());
                }
                outLine.get(in).add(prerequisite[0]); // start
                inGre[prerequisite[0]]++; // 统计每个节点的入度
            }

            Deque<Integer> queue = new LinkedList<>();
            // 找到入度为0的节点
            for (int i = 0; i < inGre.length; i++) {
                if (inGre[i] == 0) {
                    queue.offer(i);
                }
            }

            List<Integer> res = new ArrayList<>();
            while (!queue.isEmpty()) {
                Integer pop = queue.pop();
                res.add(pop);
                // 表示选课
                List<Integer> out = outLine.get(pop);
                if (out != null) {
                    for (Integer i : out) {
                        inGre[i]--;
                        if (inGre[i] == 0) {
                            queue.offer(i);
                        }
                    }
                }
            }
            return res.size() == numCourses ? res.stream().mapToInt(Integer::intValue).toArray() : new int[0];
        }
    }
}
