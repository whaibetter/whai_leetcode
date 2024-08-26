package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/26 23:45
 * @注释
 */
public class LeetCode503 {

    @Test
    public void test() {
        int[] ints = new Solution().nextGreaterElements(new int[]{1,2,1});
        for (int anInt : ints) {
            System.out.println(anInt);

        }

    }


    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int[] newNums = new int[nums.length * 2];
            for (int i = 0; i < newNums.length; i++) {
                newNums[i] = nums[i % nums.length];
            }

            int[] res = new int[nums.length * 2];
            Deque<Integer> stack = new LinkedList<>();
            Arrays.fill(res, -1);
            for (int i = 0; i < newNums.length; i++) {
                while (!stack.isEmpty() && newNums[stack.peek()] < newNums[i]) {
                    res[stack.peek()] = newNums[i];
                    stack.pop();
                }
                stack.push(i);
            }

            return Arrays.copyOf(res, nums.length);
        }
    }

}
