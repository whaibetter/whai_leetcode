package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/13 14:10
 * @注释
 */
public class LeetCode491 {
    @Test
    public void test() {
        int[] nums = {4, 6, 7, 7};
        System.out.println(new Solution().findSubsequences(nums));
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracking(nums, 0);
            return res;
        }


        /**
         * 递增
         *
         * @param nums
         * @param start
         */
        public void backTracking(int[] nums, int start) {
            if (path.size() >= 2) {
                res.add(new ArrayList<>(path));
            }
            if (start > nums.length) {
                return;
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                if (!path.isEmpty() && nums[i] < path.get(path.size() - 1)) {
                    continue;
                }
                if (set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                path.add(nums[i]);
                backTracking(nums, i + 1);
                path.remove(path.size()-1);
            }

        }
    }
}
