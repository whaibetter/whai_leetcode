package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/3 12:16
 * @注释
 */
public class LeetCode135 {

    @Test
    public void test() {
        new Solution().candy(new int[]{1, 0, 2, 4, 1});
    }

    class Solution {
        public int candy(int[] ratings) {
            // 从前往后，如果rating高，就+1糖果
            int[] distribute = new int[ratings.length];

            distribute[0] = 1;
            for (int i = 1; i < ratings.length; i++) {

                if (distribute[i]==0) distribute[i] = 1;
                if (ratings[i] > ratings[i - 1]) {
                    distribute[i] = distribute[i - 1] + 1;
                }
            }

            for (int i = distribute.length - 1; i > 0; i--) {
                // 需要同时满足 比两边都高
                if (ratings[i - 1] > ratings[i]) {
                    distribute[i - 1] = Math.max(distribute[i - 1], distribute[i] + 1);
                }
            }

            int sum = 0;
            for (int i : distribute) {
                sum += i;
            }
            return sum;
        }

    }
}
