package cn.whaifree.redo.redo.redo_24_3_16;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/16 17:42
 * @注释
 */
public class LeetCode435 {

    @Test
    public void test() {
//        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
//        int result = new Solution().eraseOverlapIntervals(intervals);
//        System.out.println(result);
//
//
//        // [ [1,2], [1,2], [1,2] ]
//        int[][] intervals1 = {{1,2},{1,2},{1,2}};
//        int result1 = new Solution().eraseOverlapIntervals(intervals1);
//        System.out.println(result1);

        // [[1,100],[11,22],[1,11],[2,12]]
        int[][] intervals2 = {{1,100},{11,22},{1,11},{2,12}};
        int result2 = new Solution().eraseOverlapIntervals(intervals2);
        System.out.println(result2);
    }


    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            // 最多能参加几次活动
            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            int participation = 1;
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < intervals[i - 1][1]) {
                    // 后一个活动的开始时间早于头一个活动开始时间，这个活动不能参加
                    intervals[i][1] = Math.min(intervals[i - 1][1], intervals[i][1]);
                }else {
                    participation++;
                }
            }

            return intervals.length - participation;

        }
    }
}
