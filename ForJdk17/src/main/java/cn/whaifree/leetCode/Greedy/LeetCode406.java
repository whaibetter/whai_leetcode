package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.*;
import java.util.function.IntFunction;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/29 11:52
 * @注释
 */
public class LeetCode406 {

    @Test
    public void test() throws InterruptedException {
        int[][] ints = new int[][]{
                {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
        };

        String gc = new String("gc");
        List<String> list = new LinkedList<>();
        int i = 0;
        while (true) {
            list.add(gc + gc + i++);
        }

//        for (int[] i : new Solution().reconstructQueue(ints)) {
//            System.out.println(Arrays.toString(i));
//        }
    }

    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            // {7,0},{4,4},{7,1},{5,0},{6,1},{5,2}
            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        // 身高相同时,前面数量少的在前
                        return o1[1] - o2[1];
                    }else {
                        return o2[0] - o1[0];
                    }
                }
            });
            for (int[] person : people) {
                System.out.println(Arrays.toString(person));
            }

            System.out.println("====");

            // 排序： [[7,0], [7,1], [6,1], [5,0], [5,2]，[4,4]]
            List<Object> list = new LinkedList<>();

            // 节点i的前面必然都比i高，那么只要把i插入到i[1]对应的位置，必然满足条件
            for (int[] p : people) {
                list.add(p[1],p);
            }

            return list.toArray(new int[people.length][]);
        }
    }

}
