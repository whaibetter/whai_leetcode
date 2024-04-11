package cn.whaifree.leetCode.LeetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/9 10:50
 * @注释
 */
public class LeetCode496 {

    @Test
    public void test() {

        ConcurrentHashMap<Object, Object> c = new ConcurrentHashMap<>();
        c.put("1", "1");

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] res = new Solution1().nextGreaterElement(nums1, nums2);
        for (int i : res) {
            System.out.println(i);
        }
    }




    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // 栈从上到下肯定是递增的

            // nums2 判断是否比stack1中的栈顶大，如果不是就进入栈2，否则

            int[] res = new int[nums1.length];
            Arrays.fill(res, -1);

            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums1.length; i++) {
                map.put(nums1[i], i);
            }

            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);

            for (int i = 1; i < nums2.length; i++) {
                if (nums2[i] < nums2[stack.peek()]) {
                    // 小于 入栈
                    stack.push(i);
                } else if (nums2[i] > nums2[stack.peek()]) {
                    // 大于，则至少stack.peek找到了结果
                    // 判断nums1中是否存在
                    // 即栈顶元素找到了 nums2中第一个大于的元素
                    while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                        if (map.containsKey(nums2[stack.peek()])) {
                            Integer loc = map.get(nums2[stack.peek()]);
                            res[loc] = nums2[i];
                        }
                        stack.pop();
                    }
                    stack.push(i);
                }else {
                    // 小于 入栈
                    stack.push(i);
                }
            }
            return res;
        }
    }

    class Solution1 {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            // nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。

            HashMap<Integer, Integer> map = new HashMap<>();
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < nums2.length; i++) {
                if (nums2[stack.peek()] < nums2[i]) {
                    while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                        int key = nums2[stack.pop()];
                        int value = nums2[i];
                        map.put(key, value);
                    }
                    stack.push(i);
                }else if (nums2[stack.peek()] > nums2[i]) {
                    stack.push(i);
                }else {
                    stack.push(i);
                }
            }

            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                if (map.containsKey(nums1[i])) {
                    res[i] = map.get(nums1[i]);
                }else {
                    res[i] = -1;
                }
            }
            return res;
        }
    }


}
