package cn.whaifree.redo.redo.redo_24_4_13;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/13 13:05
 * @注释
 */
public class LeetCode496 {

    @Test
    public void test()
    {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] res = new Solution().nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.println(i);
        }
    }

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // nums1对应位置的下一个更大元素
            Map<Integer, Integer> map = new HashMap<>();
            Deque<Integer> stack = new LinkedList<>();
            stack.push(nums2[0]);
            for (int i = 1; i < nums2.length; i++) {
                while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                    map.put(stack.pop(), nums2[i]);
                }
                stack.push(nums2[i]);
            }
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                int key = nums1[i];
                if (map.containsKey(key)) {
                    res[i] = map.get(key);
                } else {
                    res[i] = -1;
                }
            }
            return res;
        }
    }


}
