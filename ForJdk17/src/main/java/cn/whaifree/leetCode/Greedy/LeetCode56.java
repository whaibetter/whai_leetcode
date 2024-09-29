package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/5 12:09
 * @注释
 */
public class LeetCode56 {

    @Test
    public void test() {
        int[][] intervals = new int[][]{{1, 3}, {5, 6}};
        for (int[] ints : new Solution().merge(intervals)) {
            System.out.println(Arrays.toString(ints));
        }
    }

    class Solution {
        public int[][] merge(int[][] intervals) {

            Arrays.sort(intervals, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });


            List<int[]> res = new ArrayList<>();
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i - 1][1] < intervals[i][0]) {
                    res.add(intervals[i - 1]);
                } else {
                    intervals[i][0] = intervals[i - 1][0]; // 已经排序过了，所以前一个的pre一定比这个的pre大
                    intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
                }
            }
            res.add(intervals[intervals.length - 1]);

//            int[][] result = new int[res.size()][2];
//            for (int i = 0; i < res.size(); i++) {
//                result[i] = res.get(i);
//            }
//            return result;

            return res.toArray(new int[res.size()][2]);
        }

    }

}
