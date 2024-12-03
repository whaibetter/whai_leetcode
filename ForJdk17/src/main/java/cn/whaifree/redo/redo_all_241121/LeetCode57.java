package cn.whaifree.redo.redo_all_241121;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/2 16:43
 * @注释
 */
public class LeetCode57 {


    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList();
        int n = intervals.length, i = 0;
        // 区间情况分三种，小于需要插入的区间，有重合的区间，大于的区间
        // 处理小于的区间
        while(i < n && intervals[i][1] < newInterval[0]){
            list.add(intervals[i]);
            i++;
        }
        // 处理重合的区间
        while(i < n && intervals[i][0] <= newInterval[1]){
            // 合并
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        list.add(newInterval);
        // 处理大于的区间
        while(i < n && intervals[i][0] > newInterval[1]){
            list.add(intervals[i]);
            i++;
        }
        return list.toArray(new int[list.size()][]);
    }
}
