package cn.whaifree.leetCode.Graph;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 0:44
 * @注释
 */
public class LeetCode210 {
    @Test
    public void test() {
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] res = new Solution().findOrder(4, prerequisites);
        System.out.println(Arrays.toString(res));
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        int[] inDegrees = new int[numCourses];


        // 建立入度表
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++; // 记录每个节点的入度
        }
        // 入度为0的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.offer(i); // 入度为 0 的节点可以进行执行
        }
        int count = 0;  // 记录可以执行的任务数
        int[] res = new int[numCourses];  // 完整拓扑排序的执行过程

        // 根据提供的可以执行的任务（入度为 0），删除入度为 0 的节点
        while (!queue.isEmpty()){
            int curr = queue.poll(); // 拿到一个可以执行的任务
            res[count++] = curr;   //  这个任务可以执行，作为下一次执行的节点
            for (int[] p : prerequisites) {
                if (p[1] == curr){  //  {a,b} 表示 a 依赖 b b-->a
                    inDegrees[p[0]]--;
                    if (inDegrees[p[0]] == 0) queue.offer(p[0]);
                }
            }
        }
        if (count == numCourses) return res;
        return new int[0];
    }


    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                graph.add(new ArrayList<>());
            }
            int[] inGre = new int[numCourses];

            Deque<Integer> deque = new LinkedList<>();
            for (int[] prerequisite : prerequisites) {
                int course = prerequisite[0];
                int pre = prerequisite[1];
                inGre[course]++;
                graph.get(pre).add(course);
            }

            for (int i = 0; i < inGre.length; i++) {
                if (inGre[i] == 0) {
                    deque.add(i);
                }
            }

            int exec = 0;
            int[] res = new int[numCourses];
            while (!deque.isEmpty()) {
                Integer exe = deque.pop();
                res[exec] = exe;
                exec++;
                List<Integer> in = graph.get(exe);
                for (int into = 0; into < in.size(); into++) {
                    Integer intoNode = in.get(into);
                    inGre[intoNode]--;
                    if (inGre[intoNode] == 0) {
                        deque.add(intoNode);
                    }
                }
            }

            if (numCourses == exec) {
                return res;
            }
            return new int[0];
        }
    }
}
