package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/16 12:10
 * @注释
 */
public class LCR074 {

    @Test
    public void test() {

        StringBuilder stringBuilder = new StringBuilder();
        int[][] intervals = {{1, 4}, {4, 6}};
        Solution solution = new Solution();
        int[][] result = solution.merge(intervals);
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));

        }
    }


    class Solution {
        /**
         * |     |
         *    | |
         *     |  |
         *          | |
         *
         * @param intervals
         * @return
         */
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            List<int[]> result = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                int[] before = intervals[i - 1];
                int[] current = intervals[i];
                if (before[1] >= current[0]) {
                    current[0] = Math.min(before[0], current[0]);
                    current[1] = Math.max(before[1], current[1]);
                }else {
                    result.add(before);
                }
            }
            result.add(intervals[intervals.length - 1]);
            return result.toArray(new int[result.size()][]);
        }
    }

}
