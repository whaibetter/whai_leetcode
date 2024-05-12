package cn.whaifree.redo.redo.redo_24_3_1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/3 19:40
 * @注释
 */
public class LeetCode491 {

    @Test
    public void test() {
        for (List<Integer> subsequence : new Solution().findSubsequences(new int[]{4,4,3,2,1})) {
            System.out.println(subsequence);
        }
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracking(nums, 0);
            return res;
        }

        public void backTracking(int[] nums, int start) {
            if (path.size() >= 2) {
                res.add(new LinkedList<>(path));
            }
            if (start >= nums.length) {
                return;
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {

                if ((path.isEmpty() || path.getLast() <= nums[i]) && !set.contains(nums[i])) {
                    path.add(nums[i]);
                    set.add(nums[i]);
                    backTracking(nums, i + 1);
                    path.removeLast();
                }
            }

        }
    }

}
