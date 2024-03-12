package cn.whaifree.redo.redo_24_3_9;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 12:32
 * @注释
 */
public class LeetCode134 {

    @Test
    public void test() {
        int[] gas = {5,1,2,3,4};
        int[] cost = {4,4,1,5,1};
        int result = new Solution().canCompleteCircuit(gas, cost);
        System.out.println(result);
    }

    class Solution {
        /**
         *
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {

            //判断是否一定能绕圈
            int sumGas = 0;
            for (int i : gas) {
                sumGas += i;
            }
            int sumCost = 0;
            for (int i : cost) {
                sumCost += i;
            }
            if (sumGas<sumCost) return -1;

            int iHave = 0;
            int length = gas.length;
            int indexGas = 0;
            for (int i = 0; i < length; i++) {
                iHave += gas[i];
                if (iHave < cost[i]) {
                    iHave = 0;
                    indexGas = (i+1) % length;
                }else {
                    iHave -= cost[i];
                }
            }
            return indexGas;
        }
    }
}
