package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/7 21:23
 * @注释
 */
public class LeetCode406 {

    @Test
    public void test()
    {
        int[][] people = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        for (int[] ints : new Solution().reconstructQueue(people)) {
            System.out.println(Arrays.toString(ints));
        }
    }


    class Solution {
        /**
         * [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
         * @param people
         * @return
         */
        public int[][] reconstructQueue(int[][] people) {
            List<int[]> list = new ArrayList<>();
            Arrays.sort(people, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];
                }
            });

            /**
             * 遍历每一个，如果身高
             */
            for (int i = 0; i < people.length; i++) {
                list.add(people[i][1], people[i]);
            }

            return list.toArray(new int[people.length][2]);
        }

    }
}
