package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/7 21:41
 * @注释
 */
public class LeetCode435 {

    @Test
    public void test()
    {
        So3 solution = new So3();
        // [0,2],[1,3],[2,4],[3,5],[4,6]
        System.out.println(solution.eraseOverlapIntervals(new int[][]{{0, 2}, {1, 3}, {2, 4}, {3, 5}, {4, 6}}));
        System.out.println(solution.eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}}));
        System.out.println(solution.eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}}));
    }

    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                }
            });

//            int start = intervals[0][0];
            int end = intervals[0][1];
            int res = 0;
            for (int i = 1; i < intervals.length; i++) {
                int[] next = intervals[i];
                /**
                 *  |    |
                 *    | |
                 *
                 *  |    |
                 *     |    |
                 *
                 *  |    |
                 *         |    |
                 *
                 */
                int nextStart = next[0];
                int nextEnd = next[1];
                if (end <= nextStart) {
//                    start = nextStart;
                    end = nextEnd;
                }else if (nextEnd <= end) {
                    res++;
                    end = nextEnd;
                } else {
                    res++;
                }
            }
            return res;
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
            int end = intervals[0][1];
            int can = 1;
            for (int i = 1; i < intervals.length; i++) {
                int[] next = intervals[i];
                int nextStart = next[0];
                int nextEnd = next[1];
                // 如果上次活动结束比下次开始时间早，能够多参加一次
                if (nextStart >= end) {
                    can++;
                    end = nextEnd;
                }
            }

            return intervals.length - can;

        }
    }

    class So3{
        public int eraseOverlapIntervals(int[][] intervals) {
            // 最多能参加几次活动
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            int participation = 1;
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < end) {
                    // 后一个活动的开始时间早于头一个活动开始时间，这个活动不能参加
                    end = Math.min(end, intervals[i][1]);
                } else {
                    participation++;
                    end = intervals[i][1];
                }
            }

            return intervals.length - participation;

        }
    }
}
