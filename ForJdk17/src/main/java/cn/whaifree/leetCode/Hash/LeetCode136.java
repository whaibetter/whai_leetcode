package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 22:35
 * @注释
 */
public class LeetCode136 {

    class Solution {
        public int singleNumber(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
                if (integerIntegerEntry.getValue() == 1) {
                    return integerIntegerEntry.getKey();
                }
            }
            return 0;
        }
    }
    @Test
    public void test() {
        Solution1 solution = new Solution1();
        int[] nums = {7, 10, 7};
        int res = solution.singleNumber(nums);
        System.out.println(res);
    }

    class Solution1 {
        /**
         * 任何数自身作^==0
         *
         * 某个元素只出现一次以外，**其余每个元素均出现两次** ^后就为0
         *
         * @param nums
         * @return
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums) {
                // 7^7=0
                // 7^10=1101
                // 7^10^7=1010(10)
                System.out.println(Integer.toBinaryString(num));
                res ^= num;
                System.out.println(Integer.toBinaryString(res));
            }
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(10));
        System.out.println(Integer.toBinaryString(7 ^ 10));
    }
}
