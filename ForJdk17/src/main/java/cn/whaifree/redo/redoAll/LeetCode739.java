package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/26 22:57
 * @注释
 */
public class LeetCode739 {

    @Test
    public void test() {
        int[] temperatures = {30,40,50,60};
        int[] ints = new Solution().dailyTemperatures(temperatures);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }


    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);

            int[] res = new int[temperatures.length];
            for (int i = 1; i < temperatures.length; i++) {
                Integer peek = stack.peek();
                while (!stack.isEmpty() && temperatures[peek] < temperatures[i]) {
                    res[peek] = i - peek;
                    stack.pop();
                    peek = stack.peek();
                }
                stack.push(i);
            }
            return res;
        }
    }
}
