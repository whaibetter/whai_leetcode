package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Vector;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/28 12:44
 * @注释
 */
public class LeetCode135 {

    @Test
    public void tesr() {
        System.out.println(new Solution().candy(new int[]{1, 2, 2, 5, 4, 3, 2}));
    }


    class Solution {
        public int candy(int[] ratings) {
            // 从前往后遍历，遇到ratings[i]>ratings[i-1] score[i]+1
            int length = ratings.length;

            int[] scores = new int[length];
            scores[0] = 1;

            for (int i = 1; i < length; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    scores[i] = scores[i - 1] + 1;
                }else {
                    scores[i] = 1;
                }
            }

            // 从后往前
            for (int i = length -1; i > 0; i--) {
                /**
                 * 如果 ratings[i] < ratings[i - 1]，此时candyVec[i-1]（第i-1个小孩的糖果数量）就有两个选择了，
                 * -  一个是candyVec[i] + 1（从右边这个加1得到的糖果数量），
                 * -  一个是candyVec[i-1]（之前比较右孩子大于左孩子得到的糖果数量）。
                 * 如 5 3
                 *    从右边向左边，i-1可选择为 3+1 或者原来的 5，从左往右已经处理过的，
                 *      同时要满足同时保证i小孩既大于左也大于右边，那么取最大值。
                 * 同时保证i小孩既大于左也大于右边
                 */
                if (ratings[i] < ratings[i - 1]) {
                    scores[i - 1] = Math.max(scores[i] + 1, scores[i - 1]);
                }
            }

            int scoreSum = 0;
            for (int score : scores) {
                scoreSum += score;
            }

            return scoreSum;
        }
    }

}
