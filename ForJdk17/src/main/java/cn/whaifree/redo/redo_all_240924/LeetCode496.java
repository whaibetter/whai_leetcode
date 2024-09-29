package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/29 14:21
 * @注释
 */
public class LeetCode496 {

    @Test
    public void test() {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] ans = new Solution().nextGreaterElement(nums1, nums2);
        for (int i : ans) {
            System.out.println(i);
        }
    }
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 计算nums2的下一个比他大的元素，存入map
            Deque<Integer> stack = new LinkedList<>();

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums2.length; i++) {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    Integer pop = stack.pop();
                    map.put(nums2[pop], nums2[i]);
                }
                stack.push(i);
            }
            int[] ans = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                ans[i] = map.getOrDefault(nums1[i], -1);
            }
            return ans;
        }
    }
}
