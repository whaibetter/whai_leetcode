package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/6 22:56
 * @注释
 */
public class LeetCode135 {

    @Test
    public void test()
    {
        System.out.println(new Solution().candy(new int[]{1,2,2,3,1}));
    }
    class Solution {
        public int candy(int[] ratings) {

            int[] candy = new int[ratings.length];
            Arrays.fill(candy, 1);
            for (int i = 0; i < ratings.length - 1; i++) {
                if (ratings[i] < ratings[i + 1]) {
                    candy[i + 1] = candy[i] + 1;
                }
            }

            for (int i = ratings.length - 1; i > 0; i--) {
                if (ratings[i - 1] > ratings[i]) {
                    candy[i - 1] = Math.max(candy[i - 1], candy[i] + 1);
                    // candy[i-1]为原来从左到右遍历的值，需要记录保存
                }
            }
            return Arrays.stream(candy).sum();
        }
    }

}
