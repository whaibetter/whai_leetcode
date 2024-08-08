package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/8 21:46
 * @注释
 */
public class LeetCode452 {

    @Test
    public void test()
    {
        // [[10,16],[2,8],[1,6],[7,12]]
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
//        System.out.println(findMinArrowShots(points));
        // [[-2147483646,-2147483645],[2147483646,2147483647]]
        int[][] points1 = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(points1));
        System.out.println(-2147483645-2147483647);
    }

    public int findMinArrowShots(int[][] points)
    {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? -1 : 1;
//                if (o1[1]<o2[1])return -1;
//                return 1;
            }
        });

        /**
         * |   |
         *   | |
         *    |     |
         *       |      |
         */
        int end = points[0][1];
        int arrows = 1;
        for (int i = 1; i < points.length; i++) {
            int nextStart = points[i][0];
            if (end < nextStart) {
                end = points[i][1];
                arrows++;
            }
        }
        return arrows;
    }
}
