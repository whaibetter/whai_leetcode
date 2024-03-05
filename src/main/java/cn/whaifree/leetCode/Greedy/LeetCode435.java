package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/4 15:46
 * @注释
 */
public class LeetCode435 {

    @Test
    public void test() {
        // intervals = [[1,2],[2,3],[3,4],[1,3]]
        int[][] ints = {{1, 2}, {1, 3}, {2, 3}, {3, 4}};
        System.out.println(new Solution1().eraseOverlapIntervals(ints));
    }

    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            int sub = 0;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] <= intervals[i - 1][1]) {
                    sub++;
                    intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
                }else {
                    intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
                }
            }

            return sub;
        }

    }

    class Solution1 {

        /**
         * 想象成一次性最多参加几个活动
         * 按照结束时间排序。
         *
         * 每次right边界就是取最早活动结束的时间；
         * 如果某次活动开始时间刚刚好比上一个活动结束的时间要晚，那么就能多参加一次活动
         *
         * @param intervals
         * @return
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            int normalCount = 1;
            int right = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                // 如果这次活动的开始时间比上个活动结束的时间要早，那么这个活动就不参加了
                if (intervals[i][0] >= right) {
                    // 如果这次活动的开始时间比上个活动结束的时间要晚，那么这个活动就可以参加
                    // 最新的结束时间更新为这个活动的结束时间
                    right = intervals[i][1];
                    normalCount++;
                }
            }
            return intervals.length - normalCount;
        }

    }

    class Solution2 {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });

            int normalCount = 1;
            for (int i = 1; i < intervals.length; i++) {

                if (intervals[i - 1][1] <= intervals[i][0]) {
                    normalCount++;
                } else {
                    // i的右边界有不重合，则正常区间+1
                    intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                }
            }

            return intervals.length - normalCount;
        }

    }

}
