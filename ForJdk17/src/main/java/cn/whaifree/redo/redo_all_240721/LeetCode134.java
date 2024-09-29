package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/5 23:08
 * @注释
 */
public class LeetCode134 {

    @Test
    public void test()
    {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(new Solution().canCompleteCircuit(gas, cost));
    }

    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {

            // 理论上，如果汽油够，一定能绕圈
            int sumGas = Arrays.stream(gas).parallel().sum();
            int sumCost = Arrays.stream(cost).parallel().sum();
            if (sumGas < sumCost) {
                return -1;
            }

            int nowHave = 0;
            int nowStart = 0;
            for (int i = 0; i < gas.length; i++) {
                nowHave += gas[i];
                if (nowHave < cost[i]) {
                    nowHave = 0;
                    nowStart = (i + 1) % gas.length;
                } else {
                    nowHave -= cost[i];
                }
            }
            return nowStart;
        }
    }
}
