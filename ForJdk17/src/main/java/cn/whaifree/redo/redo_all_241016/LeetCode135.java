package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 16:55
 * @注释
 */
public class LeetCode135 {

    @Test
    public void test() {
        int[] ratings = {1, 0, 2};
        // 1 2 3 4 3 2 1
        int result = new Solution().candy(ratings);
        System.out.println(result);
    }


    class Solution {
        /**
         * 先全部分配1
         *
         * 1 0 2
         * 1 1 1
         * 2 1 1
         * 2 1 2
         *
         * @param ratings
         * @return
         */
        public int candy(int[] ratings) {
            int[] dis = new int[ratings.length];
            Arrays.fill(dis, 1);
            for (int i = 0; i < ratings.length - 1; i++) {
                if (ratings[i + 1] > ratings[i]) {
                    dis[i + 1] = dis[i] + 1;
                }
            }
            for (int j = ratings.length - 2; j >= 0; j--) {
                if (ratings[j] > ratings[j + 1]) {
                    dis[j] = Math.max(dis[j], dis[j + 1] + 1); // 保留之前的
                }
            }
            System.out.println(Arrays.toString(dis));

            return Arrays.stream(dis).sum();
        }
    }
}
