package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.HashSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 14:20
 * @注释
 */
public class LCR186 {
    @Test
    public void test() {
        Solution solution = new Solution();
        int[] places = {0, 6, 9, 0, 7};
        System.out.println(solution.checkDynasty(places));
    }

    class Solution {
        /**
         * 给你5个数字，判断是否是顺子
         * @param places
         * @return
         */
        public boolean checkDynasty(int[] places) {
//            Arrays.sort(places);

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < places.length; i++) {
                if (places[i] == 0) {
                    continue;
                }
                if (set.contains(places[i])) {
                    return false;
                } else {
                    set.add(places[i]);
                }
                min = Math.min(min, places[i]);
                max = Math.max(max, places[i]);
            }
            // 0 0 7 8 9
            // 7 0 0 10 12
            return max - min  < 5; // 在这个区间，并且没有重复，则必然会顺子
        }
    }

}
