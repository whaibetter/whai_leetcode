package cn.whaifree.redo.redo.redo_24_3_9;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/9 13:22
 * @注释
 */
public class LeetCode452 {

    @Test
    public void test()
    {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        int[][] points1 = {{1, 2}, {3, 4}, {5, 6},{7,8}};
        int[][] points2 = {{1,2},{2,3},{3,4},{4,5}};
        System.out.println(new Solution().findMinArrowShots(points2));
    }


    class Solution {
        /**
         * [[-2147483646,-2147483645],[2147483646,2147483647]]
         * @param points
         * @return
         */
        public int findMinArrowShots(int[][] points) {
            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[1]<o2[1])return -1;
                    return 1;
                }
            });

            int arrow = 1;
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] <= points[i - 1][1]) {
                    // 在这个区间内
                    points[i][1] = points[i - 1][1];
                }else {
                    arrow++;
                }

            }
            return arrow;
        }
    }
}
