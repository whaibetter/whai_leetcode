package cn.whaifree.redo.redo_all_240924;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/11 11:49
 * @注释
 */
public class LeetCode274 {

    class Solution {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);

            // 0 1 3 5 6
            // | i | h |
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
