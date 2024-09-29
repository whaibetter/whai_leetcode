package cn.whaifree.leetCode.Graph;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 0:02
 * @注释
 */
public class LeetCode207 {

    @Test
    public void test() {
        int numCourses = 4;
        int[][] prerequisites = {{1, 0},{0,2},{1,3}};
        System.out.println(new Solution().canFinish(numCourses, prerequisites));
    }
    class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }

            // 统计入度个数
            int[] inGre = new int[numCourses];
            for (int i = 0; i < prerequisites.length; i++) {
                int course = prerequisites[i][0];
                int preCourse = prerequisites[i][1];
                graph.get(preCourse).add(course);
                inGre[course]++;
            }

            // 对所有入度为0的进入队列
            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (inGre[i] == 0) {
                    queue.add(i);
                }
            }
            // 出对，并去边
            int exeCount = 0;
            while (!queue.isEmpty()) {
                Integer pop = queue.pop();
                exeCount++;
                // 遍历这个pop点的出边
                List<Integer> popOut = graph.get(pop);
                for (int i = 0; i < popOut.size(); i++) {
                    int deleteSideNode = popOut.get(i);
                    inGre[deleteSideNode]--;
                    if (inGre[deleteSideNode] == 0) {
                        queue.add(deleteSideNode);
                    }
                }
            }
            // 如果队列中没有元素了，但还有边，返回false
            return exeCount == numCourses;
        }
    }


    class Solution1 {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 统计入度个数
            int[] map = new int[2000];
            for (int i = 0; i < prerequisites.length; i++) {
                map[prerequisites[i][0]]++;
            }
            // 对所有入度为0的进入队列
            Deque<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < numCourses; i++) {
                if (map[i] == 0) {
                    queue.add(i);
                }
            }
            // 出对，并去边
            int exeCount = 0;
            while (!queue.isEmpty()) {
                Integer pop = queue.pop();
                exeCount++;
                // 遍历所有的边
                for (int i = 0; i < prerequisites.length; i++) {
                    if (prerequisites[i][1] == pop) {
                        int deleteSideNode = prerequisites[i][0];
                        map[deleteSideNode]--;
                        if (map[deleteSideNode] == 0) {
                            queue.add(deleteSideNode);
                        }
                    }
                }
            }
            // 如果队列中没有元素了，但还有边，返回false
            return exeCount == numCourses;
        }
    }
}
