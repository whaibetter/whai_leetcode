package cn.whaifree.leetCode.MonotoneStack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/8 11:48
 * @注释
 */
public class LeetCode739 {

    @Test
    public void test()
    {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        Solution solution = new Solution();
        int[] res = solution.dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res));

    }

    class Solution {
        /**
         * 单调栈
         *
         * 不断把i放入栈
         * if nums[i] > stack.peek
         *      pop = stack.pop
         *      res[] = i - pop
         * else
         *      stack.push(i)
         *
         * @param temperatures
         * @return
         */
        public int[] dailyTemperatures(int[] temperatures) {
            int[] res = new int[temperatures.length];
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < temperatures.length ; i++) {
                if (temperatures[i] > temperatures[stack.peek()]) {
                    // 如果当前比遍历元素比 栈顶的大，证明该元素就是栈顶元素右边第一个大于他的值
                    // 并且需要判断一下，当前元素是不是 新栈顶 的下一个大于他的
                    while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                        res[stack.peek()] = i - stack.peek();
                        stack.pop();
                    }
                }
                stack.push(i);
            }
            return res;
        }
    }
}
