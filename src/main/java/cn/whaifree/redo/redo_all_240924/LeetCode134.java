package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/25 17:53
 * @注释
 */
public class LeetCode134 {
    @Test
    public void test() {
        int[] gas = {4,5, 1, 2, 3, };
        int[] cost = { 1,2, 3, 4, 5,};
        System.out.println(new Solution().canCompleteCircuit(gas, cost));
    }


    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int sumGas = 0;
            for (int j : gas) {
                sumGas += j;
            }
            int sumCost = 0;
            for (int j : cost) {
                sumCost += j;
            }
            if (sumCost > sumGas) {
                return -1;
            }
            // 包能走到
            int now = 0;
            int startNow = 0;
            for (int i = 0; i < gas.length; i++) {
                now += gas[i];
                if (now < cost[i]) {
                    now = 0;
                    startNow = i + 1;
                }else {
                    now -= cost[i];
                }
            }
            return startNow;
        }
    }
}
