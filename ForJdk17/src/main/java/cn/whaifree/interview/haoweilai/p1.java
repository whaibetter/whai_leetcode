package cn.whaifree.interview.haoweilai;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/7 22:24
 * @注释
 */
public class p1 {

    @Test
    public void test() {
        System.out.println(tribonacci(25));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型
     * @return int整型
     */
    public int tribonacci (int n) {

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}


class c2{


    public static void main(String[] args) {

        c2 c2 = new c2();
        System.out.println(Arrays.toString(c2.twoSum(new int[]{1, -2, -3, -4}, -5)));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum (int[] nums, int target) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (map.containsKey(need)) {
                return new int[]{i, map.get(need)};
            }
        }
        return null;
    }
}
