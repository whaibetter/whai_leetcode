package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/3 11:57
 * @注释
 */
public class LeetCode860 {

    @Test
    public void test() {
        System.out.println(new Solution().lemonadeChange(new int[]{5,5,10,10,20}));
    }

    class Solution {
        public boolean lemonadeChange(int[] bills) {

            int haveFive = 0;
            int haveTen = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    haveFive++;
                } else if (bill == 10) {
                    haveTen++;
                    haveFive--;
                } else if (bill == 20) {
                    if (haveTen >= 1) {
                        haveTen--;
                        haveFive--;
                    }else {
                        haveFive -= 3;
                    }
                }
                if (haveFive < 0 || haveTen < 0) {
                    return false;
                }
            }
            return true;

        }
    }
}
