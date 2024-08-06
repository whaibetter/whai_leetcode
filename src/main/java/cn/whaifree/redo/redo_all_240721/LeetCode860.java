package cn.whaifree.redo.redo_all_240721;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/6 22:51
 * @注释
 */
public class LeetCode860 {

    class Solution {
        public boolean lemonadeChange(int[] bills) {
            int iHave5 = 0;
            int iHave10 = 0;
            for (int bill : bills) {
                if (bill == 5) {
                    iHave5++;
                } else if (bill == 10) {
                    iHave5--;
                    iHave10++;
                } else if (bill == 20) {

                    if (iHave10 > 0) {
                        iHave10--;
                        iHave5--;
                    }else {
                        iHave5 -= 3;
                    }

                }
                if (iHave5 < 0 || iHave10 < 0) {
                    return false;
                }

            }
            return true;
        }
    }
}
