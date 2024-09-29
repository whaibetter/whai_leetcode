package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/28 13:22
 * @注释
 */
public class LeetCode860 {

    @Test
    public void test() {
        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }

    class Solution {

        /**
         * bills[i] 不是 5 就是 10 或是 20
         *
         * @param bills
         * @return
         */
        public boolean lemonadeChange(int[] bills) {

            int fiveHave = 0; // 有5元的钞票数
            int tenHave = 0; // 10元钞票数
            for (int i = 0; i < bills.length; i++) {

                if (bills[i] == 5) { // 5元直接收下
                    fiveHave++;
                } else if (bills[i] == 10) { // 10元则找5元
                    tenHave++;
                    fiveHave--;
                } else if (bills[i] == 20) { // 20元可以选择给他10+5或者3个5元
                    if (tenHave >= 1) {
                        tenHave--;
                        fiveHave--;
                    } else {
                        fiveHave -= 3;
                    }
                }
                // 如果钞票不够找
                if (fiveHave < 0 || tenHave < 0) {
                    return false;
                }
            }
            return true;
        }


    }


}
