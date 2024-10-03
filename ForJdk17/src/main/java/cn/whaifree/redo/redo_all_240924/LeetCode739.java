package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/30 10:33
 * @注释
 */
public class LeetCode739 {

    @Test
    public void test() {
        int[] dailyTemperatures = new Solution().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        for (int i : dailyTemperatures) {
            System.out.println(i);
        }
    }

    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {

            Deque<Integer> deque = new LinkedList<>();
            int[] res = new int[temperatures.length];
            for (int i = 0; i < temperatures.length; i++) {
                while (!deque.isEmpty() && temperatures[deque.peek()] < temperatures[i]) {
                    Integer pop = deque.pop();
                    res[pop] = i - pop;
                }
                deque.push(i);
            }
            return res;
        }
    }
}
