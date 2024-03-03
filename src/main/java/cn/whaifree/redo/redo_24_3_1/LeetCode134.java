package cn.whaifree.redo.redo_24_3_1;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/1 12:07
 * @注释
 */
public class LeetCode134 {


    public static void main(String[] args) {
        
    }

    @Test
    public void test() {
        System.out.println(new Solution().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(new Solution().canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1}));

    }

    class Solution {
        /**
         * 1. 判断能否循环
         * 2. 如果一定可以循环，找到第一个能剩下油量的
         * @param gas
         * @param cost
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            // 计算差值
            int length = gas.length;
            int[] rent = new int[length];
            int rentGas = 0;
            for (int i = 0; i < length; i++) {
                rent[i] = gas[i] - cost[i];
                rentGas += rent[i];
            }
            if (rentGas<0){
                return -1;
            }

            int have = 0;
            int resIndex = 0;
            for (int i = 0; i < length; i++) {
                have += rent[i];
                if (have < 0) {
                    // 油量不够,从下一个点开始
                    resIndex = (i + 1) % length;
                    have = 0;
                }
            }
            // [a,b] a内有不够用的时候，b内也有可能有不够用的时候，用index标记
            return resIndex;
        }

    }
}
