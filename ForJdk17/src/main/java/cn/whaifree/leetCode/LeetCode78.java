package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/18 10:29
 * @注释
 */
public class LeetCode78 {

    @Test
    public void test() {
        System.out.println(new Solution().subsets(new int[]{1, 2, 3}));
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        public List<List<Integer>> subsets(int[] nums) {
            backTracking(nums, 0);
            return res;
        }

        void backTracking(int[] nums, int start) {
            res.add(new ArrayList<>(temp));
            if (start >= nums.length) {
                return;
            }

            for (int i = start; i < nums.length ; i++) {
                temp.add(nums[i]);
                backTracking(nums,i+1);
                temp.remove(temp.size() - 1);
            }

        }
    }
}
