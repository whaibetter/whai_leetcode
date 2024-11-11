package cn.whaifree.redo.redo_all_241016;

import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/9 18:20
 * @注释
 */
public class LCR186 {

    class Solution {
        public boolean checkDynasty(int[] places) {
            int[] set = new int[14];
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < places.length; i++) {
                if (places[i] == 0) {
                    continue;
                    // 差值为5以内，并且不重复，就能推出这是个顺子
                }
                if (set[places[i]] != 0) {
                    // 已经存在，直接返回
                    return false;
                }
                set[places[i]]++;
                min = Math.min(min, places[i]);
                max = Math.max(max, places[i]);
            }
            return max - min < 5;
        }
    }
}
