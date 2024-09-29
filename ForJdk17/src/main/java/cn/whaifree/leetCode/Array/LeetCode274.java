package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 17:17
 * @注释
 */
public class LeetCode274 {
    @Test
    public void test()
    {
        int[] nums = new int[]{1,3,1};
        System.out.println(new Solution().hIndex(nums));
    }

    class Solution {
        public int hIndex(int[] citations) {

            Arrays.sort(citations);

            for (int i = 0; i < citations.length; i++) {
                int h = citations.length - i;
                if (citations[i] >= h) {
                    return h;
                }
            }
            return 0;
        }
    }
}
