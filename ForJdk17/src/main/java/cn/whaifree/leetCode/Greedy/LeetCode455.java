package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/24 13:04
 * @注释
 */
public class LeetCode455 {

    @Test
    public void test() {
        System.out.println(new Solution().findContentChildren(new int[]{1, 2, 3,1}, new int[]{2,3,1}));
    }

    class Solution {
        /**
         *
         * @param g 孩子的胃口
         * @param s 饼干
         * @return
         */
        public int findContentChildren(int[] g, int[] s) {
            // 找到g中最大的，分配s中最大的看看是否满足
            Arrays.sort(g);
            Arrays.sort(s);

            int res = 0;


            int gIndex = g.length-1;
            int sIndex = s.length - 1;
            while (sIndex >= 0 && gIndex >= 0) {

                // 饼干可以分配，就让两个指针--
                if (s[sIndex] >= g[gIndex]) {
                    res++;
                    sIndex--;
                    gIndex--;
                } else {
                    // 饼干不能分配，就分配给更小的孩子
                    gIndex--;
                }
            }

            return res;
        }




    }


}
