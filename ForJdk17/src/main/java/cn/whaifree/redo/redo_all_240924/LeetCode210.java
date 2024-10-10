package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 12:25
 * @注释
 */
public class LeetCode210 {

    @Test
    public void test() {
        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};
        int[] ints = new Solution().findOrder(4, prerequisites);
        System.out.println(Arrays.toString(ints));
    }

    class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 统计所有入度
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int[] prerequisite : prerequisites) {
                if (!map.containsKey(prerequisite[0])) {
                    map.put(prerequisite[0], new ArrayList<>());
                }
                map.get(prerequisite[0]).add(prerequisite[1]);
            }

            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (!map.containsKey(i)) {
                    deque.add(i);
                }
            }

            List<Integer> list = new ArrayList<>();
            int exec = 0;
            while (!deque.isEmpty()) {
                Integer pop = deque.pop();
                list.add(pop);
                exec++;
                for (int[] prerequisite : prerequisites) {
                    int start = prerequisite[1]; // 出
                    int end = prerequisite[0]; // 入
                    if (start == pop) {
                        List<Integer> item = map.get(end);
                        item.remove(Integer.valueOf(start));
                        // 如果此时item还有元素，则不加入队列
                        if (item.isEmpty()) {
                            deque.add(end);
                        }
                    }
                }
            }

            if (exec != numCourses) {
                return new int[]{};
            }

            if (list.size() == numCourses) {
                return list.stream().mapToInt(i -> i).toArray();
            }

            return new int[]{};
        }
    }
}
