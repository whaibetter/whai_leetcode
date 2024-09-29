package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/4 14:25
 * @注释
 */
public class LeetCode452 {

    @Test
    public void test() {
        // [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
        int[][] ints = {
                {3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}
        };


        System.out.println(new Solution1().findMinArrowShots(ints));
    }

    class Solution {
        /**
         *
         * 按照最近出从小到大排序，判断下一个区间是否有交集
         * - 有交集，取交集最小值作为都能bomb的点
         * - 没有交集 res++表示下一个区间的箭
         * @param points
         * @return
         */
        public int findMinArrowShots(int[][] points) {
            // 先按首位从小到大排序，end为start结束，此区间全部bomb
            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Long.compare(o1[0], o2[0]);
                }
            });

            int res = 1;
            for (int i = 1; i < points.length; i++) {

                if (points[i][0] < points[i - 1][1]) {
                    // 新的区间，有交集
                    points[i][1] = Math.min(points[i][1], points[i - 1][1]);
                }else {
                    res++;
                }

            }

            return res;
        }

    }

    class Solution1 {
        /**
         * 按照最远处排序，判断下一元素是否在上一个的区间内，不是就res++
         * @param points
         * @return
         */
        public int findMinArrowShots(int[][] points) {
            //按最远处从小到大排序
            Arrays.sort(points, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    //-2147483646,-2147483645],[2147483646,2147483647 这个用例
                    return Long.compare(o1[1], o2[1]);
                }
            });

            int res = 1;
            int right = points[0][1];
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] <= right) {
                    //当前箭能够射穿这个气球
                    continue;
                }
                res++;
                right = points[i][1];
            }

            return res;
        }

    }
}
