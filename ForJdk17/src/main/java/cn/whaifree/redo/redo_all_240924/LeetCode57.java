package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/6 11:49
 * @注释
 */
public class LeetCode57 {

    class Solution {
        /**
         * | |  |  |  |   |
         *   |       |
         *
         * @param intervals
         * @param newInterval
         * @return
         */
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int[][] res = new int[intervals.length + 1][2];
            int i = 0;
            int index = 0;
            // 找到newInterval的左边界位置
            while (intervals[index][0] < newInterval[0]) {
                res[i++] = intervals[index];
                index++;
            }
            // 此时index的value >= newInterval的起始位置

            // 找到newInterval右边边界位置
            while (intervals[index][1] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[index][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
                index ++;
            }

            res[i++] = newInterval;
            // 把newInterval右边界右边的位置全部移动过来
            while (index < intervals.length) {
                res[i++] = intervals[index];
                index++;
            }
            return Arrays.copyOf(res, i);
        }
    }


}
