package cn.whaifree.redo.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 13:37
 * @注释
 */
public class LeetCode739 {

    @Test
    public void test()
    {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ints = new Solution().dailyTemperatures(temperatures);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


    // 下一个更高温度出现在几天后
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] ans = new int[temperatures.length];
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < temperatures.length; i++) {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    Integer pop = stack.pop();
                    ans[pop] = i - pop;
                }
                stack.push(i);
            }
            return ans;
        }
    }
}
