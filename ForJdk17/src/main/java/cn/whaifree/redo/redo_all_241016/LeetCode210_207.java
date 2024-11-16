package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/15 9:56
 * @注释
 */
public class LeetCode210_207 {

    @Test
    public void test() {
        int numCourses = 2;
        int[][] prerequisites = {};
        Solution solution = new Solution();
        int[] ints = solution.findOrder(numCourses, prerequisites);
        System.out.println(Arrays.toString(ints));
    }

    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi

            Map<Integer, List<Integer>> map = new HashMap<>();
            int[] in = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                map.put(i, new ArrayList<>());
            }

            for (int[] prerequisite : prerequisites) {
                in[prerequisite[0]]++;
                map.get(prerequisite[1]).add(prerequisite[0]);
            }

            Deque<Integer> deque = new ArrayDeque<>();
            // 入度炜0的
            for (int i = 0; i < in.length; i++) {
                if (in[i] == 0) {
                    deque.push(i);
                }
            }

            int[] res = new int[numCourses];
            int index = 0;
            while (!deque.isEmpty()) {
                // 入度为0
                Integer pop = deque.pop();
                res[index++] = pop;

                List<Integer> into = map.get(pop);
                for (Integer i : into) {
                    in[i]--;
                    if (in[i] == 0) {
                        deque.add(i);
                    }
                }
            }

            if (index != numCourses) {
                return new int[0];
            }
            return res;
        }
    }

    class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi

            Map<Integer, List<Integer>> map = new HashMap<>();
            int[] in = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                map.put(i, new ArrayList<>());
            }

            for (int[] prerequisite : prerequisites) {
                in[prerequisite[0]]++;
                map.get(prerequisite[1]).add(prerequisite[0]);
            }

            Deque<Integer> deque = new ArrayDeque<>();
            // 入度炜0的
            for (int i = 0; i < in.length; i++) {
                if (in[i] == 0) {
                    deque.push(i);
                }
            }

            int[] res = new int[numCourses];
            int index = 0;
            while (!deque.isEmpty()) {
                // 入度为0
                Integer pop = deque.pop();
                res[index++] = pop;

                List<Integer> into = map.get(pop);
                for (Integer i : into) {
                    in[i]--;
                    if (in[i] == 0) {
                        deque.add(i);
                    }
                }
            }

            if (index != numCourses) {
                return false;
            }
            return true;
        }
    }

}
