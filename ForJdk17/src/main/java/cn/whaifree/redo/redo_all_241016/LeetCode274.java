package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/29 12:25
 * @注释
 */
public class LeetCode274 {

    @Test
    public void test() {
        int[] citations = {2};
        int res = new Solution().hIndex(citations);
        System.out.println(res);
    }
    class Solution {
        /**
         * 0 1 3 5 6
         * @param citations
         * @return
         */
        public int hIndex(int[] citations) {

            Arrays.sort(citations);


            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= citations.length - i) {
                    return citations.length - i;
                }
            }

            return 0;
        }
    }

}
