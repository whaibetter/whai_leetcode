package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 23:37
 * @注释
 */
public class LeetCode57 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int[][] insert = solution.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        for (int[] ints : insert) {
            System.out.println(Arrays.toString(ints));
        }
        // [[1,2],[3,5],[6,7],[8,10],[12,16]]
        int[][] insert1 = solution.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        for (int[] ints : insert1) {
            System.out.println(Arrays.toString(ints));
        }
    }

    class Solution {
        /**
         * |      |    |   |
                |        |
         *
         * |      |        |   |
         *      |      |
         *
         * |       |      |    |
         *           |   |
         *
         * |       |      |     |
         *              |    |
         *
         *
         *   |    |
         *      |    |
         *             |    |
         * |    |
         *    |    |
         *        |    |
         * @param intervals
         * @param newInterval
         * @return
         */
        public int[][] insert(int[][] intervals, int[] newInterval) {

            List<int[]> list = new ArrayList<>(Arrays.asList(intervals));
            list.add(newInterval);
            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            for (int i = 1; i < list.size(); i++) {
                int[] now = list.get(i);
                int[] before = list.get(i - 1);
                if (now[0] <= before[1]) {
                    before[1] = Math.max(before[1], now[1]);
                    list.remove(i);
                    i--;
                }
            }
            return list.toArray(new int[list.size()][2]);
        }
    }
}
