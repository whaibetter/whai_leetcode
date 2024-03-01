package cn.whaifree.leetCode.Greedy;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/27 20:02
 * @注释
 */
public class LeetCode134 {

    @Test
    public void test() throws InterruptedException {

        System.out.println("初始化内存 -Xms");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m");

        System.out.println("最大可使用内存 ：");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m");

        System.out.println("最大堆内存：-Xmx");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m");

        // -XX+PrintGCDetails

        int[] ints = new int[10000];
//        Thread.sleep(1000000);

//        System.out.println(new Solution().canCompleteCircuit(
//                new int[]{5,1,2,3,4},
//                new int[]{4,4,1,5,1}
//        ));

    }

    class Solution {
        /**
         *
         * @param gas 加油站有的油
         * @param cost 行驶代价
         * @return
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {

            int[] rent = new int[gas.length];
            int total = 0;
            for (int i = 0; i < gas.length; i++) {
                rent[i] = gas[i] - cost[i];
                total += rent[i];
            }
            // 如果全部剩余<0 则必然跑不了一圈
            if (total < 0) {
                return -1;
            }


            // 以下为必然可以跑一圈的
            // 如果当前剩余不够用，则让指针指向i+1
            int curSum = 0;
            int index = 0;
            for (int i = 0; i < rent.length; i++) {
                curSum += rent[i];
                if (curSum < 0) {
                    index = (i + 1) % gas.length ;
                    curSum = 0;
                }
            }
            // 1. 前提，必然能跑一圈
            // 2. 没有进入某个i之后都没有curSum<0 那么就是正确的i
            return index;
        }

    }
//    class Solution {
//        /**
//         *
//         * @param gas 加油站有的油
//         * @param cost 行驶代价
//         * @return
//         */
//        public int canCompleteCircuit(int[] gas, int[] cost) {
//            int needSum = 0;
//            for (int i : cost) {
//                needSum += i;
//            }
//
//            int iHave = 0;
//            int sumAdd = 0;
//            int start = 0;
//            for (int i = 0; i < gas.length; i++) {
//                iHave += gas[i];
//                sumAdd += gas[i];
//                if (iHave < cost[i]) {
//                    iHave = 0;
//                    i = start;
//                    start = start + 1;
//                    sumAdd = 0;
//                    continue;
//                }
//                iHave -= cost[i];
//                if (sumAdd >= needSum) {
//                    return start;
//                }
//
//                if (i == gas.length - 1) {
//                    i = -1;
//                }
//
//                if (start == gas.length - 1 && start != i) {
//                    break;
//                }
//            }
//
//            return -1;
//        }
//
//    }

}
