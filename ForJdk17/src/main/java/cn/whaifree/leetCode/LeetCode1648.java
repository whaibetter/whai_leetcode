package cn.whaifree.leetCode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/12 11:47
 * @注释
 */
public class LeetCode1648 {

//    @Test
//    public void test() {
//        int[] inventory = {1000000000};
//        int res = new Solution().maxProfit(inventory, 1000000000);
//        System.out.println(res);
//    }
//
//    class Solution {
//
//
//        /**
//         * 数量最多的同色球的数量 和 球数 == MAX 的颜色的数量。
//         *
//         * @param inventory
//         * @param orders
//         * @return
//         */
//        public int maxProfit(int[] inventory, int orders) {
//
//            Arrays.sort(inventory);
//
//            int res = 0;
//            int countOfColor = 1;
//            for (int i = inventory.length - 1; i > 0; i--) {
//                int end = inventory[i];
//                int start = inventory[i - 1];
//                for (int j = end; j > start; j--) {
//                    for (int x = 0; x < countOfColor; x++) {
//                        res += j;
//                        inventory[i]--;
//                        orders--;
//                        if (orders == 0) {
//                            return res;
//                        }
//                    }
//                }
//                countOfColor++;
//            }
//
//            // 最后变为 x x x x （x为最小值）
//
//
//            for (int j = 0; j < inventory[0]; j++) {
//                int item = inventory[0];
//                if (orders < inventory.length) {
//                    return res + orders * item;
//                }else {
//                    for (int i = 0; i < item; i++) {
//                        res += inventory[0];
//                        orders--;
//                        if (orders == 0) {
//                            return res;
//                        }
//                    }
//                }
//            }
//
//            return res;
//
//
//            // 5 2 2 1 2 1
//
//
////            int res = 0;
////            int add = 1;
////
////            int right = inventory.length - 1;
////            for (int i = 0; i < orders; i++) {
////                int left = right - 1;
////
////                int leftV = inventory[left];
////                int rightV = inventory[right];
////                for (int j = rightV; j > leftV; j--) {
////
////                }
////                right--;
////            }
////            return res;
//
//        }
//    }

}
