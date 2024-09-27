package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 11:37
 * @注释
 */
public class LeetCode135 {

    class Solution {
        /**
         *
         *
         * @param ratings
         * @return
         */
        public int candy(int[] ratings) {

            int[] distribute = new int[ratings.length];
            Arrays.fill(distribute, 1);
            for (int i = 1; i < ratings.length; i++) {
                if (distribute[i] > distribute[i - 1]) {
                    distribute[i] = distribute[i - 1] + 1;
                }
            }

            for (int i = ratings.length - 2; i >= 0; i++) {
                if (distribute[i] > distribute[i + 1]) {
                    distribute[i] = Math.max(distribute[i], distribute[i + 1] + 1);
                }
            }
            return Arrays.stream(distribute).sum();

        }
    }
}
