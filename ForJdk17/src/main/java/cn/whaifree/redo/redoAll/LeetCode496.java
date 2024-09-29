package cn.whaifree.redo.redoAll;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/26 23:30
 * @注释
 */
public class LeetCode496 {

    @Test
    public void test() {
        int[] ints = new Solution().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {

            HashMap<Integer, Integer> map = new HashMap<>();

            Deque<Integer> stack = new LinkedList<>();
            int[] res = new int[nums2.length];
            Arrays.fill(res, -1);
            for (int i = 0; i < nums2.length; i++) {
                map.put(nums2[i], i);
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    res[stack.peek()] = nums2[i];
                    stack.pop();
                }
                stack.push(i);
            }

            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                result[i] = res[map.get(nums1[i])];
            }

            return result;
        }
    }
}
