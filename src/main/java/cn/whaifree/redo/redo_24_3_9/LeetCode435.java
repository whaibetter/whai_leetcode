package cn.whaifree.redo.redo_24_3_9;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/9 12:25
 * @注释
 */
public class LeetCode435 {

    @Test
    public void test() {
        // [[1,2],[2,3],[3,4],[1,3]]
//        int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals = new int[][]{{1, 4}, {3, 6}, {7, 10},{5, 8},  {9, 12}};

        // [ [1,2], [1,2], [1,2] ]
//        int[][] intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println(new Solution1().eraseOverlapIntervals(intervals));
    }

    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 按照起始位置从小到大排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            // 如果i的起始位置比i-1的终点位置要小，那么就需要移除，并更新结束位置为min（last,last）
            // 如果起始位置比i-1大，那么直接进入下个区间，并count++

            int normalCount = 1;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < intervals[i - 1][1]) {
                    // 需要去掉
                    intervals[i][1] = Math.min(intervals[i][1], intervals[i - 1][1]);
                }else {
                    normalCount++;
                }
            }
            return intervals.length - normalCount;

        }
    }

    class Solution1 {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 按照起始位置从小到大排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            int right = intervals[0][1];
            int count = 1;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < right) {
                    // 需要去掉
//                    right = right;
                }else {
                    right = intervals[i][1];
                    count++;
                }
            }
            return intervals.length - count;
        }
    }
}
