package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 12:20
 * @注释
 */
public class LeetCode406 {

    @Test
    public void test()
    {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        Solution solution = new Solution();
        int[][] result = solution.reconstructQueue(people);
        System.out.println(Arrays.deepToString(result));
    }

    class Solution {
        public int[][] reconstructQueue(int[][] people) {

            Arrays.sort(people, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o2[0] == o1[0]) {
                        return o1[1] - o2[1];
                    }
                    return o2[0] - o1[0];
                }
            });

            List<int[]> list = new ArrayList<>();
            for (int[] person : people) {
                list.add(person[1], person);
            }


            return list.toArray(new int[list.size()][2]);
        }
    }

}
