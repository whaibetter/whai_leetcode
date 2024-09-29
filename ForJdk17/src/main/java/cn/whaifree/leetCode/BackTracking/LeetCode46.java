package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/19 11:01
 * @注释
 */
public class LeetCode46 {
    @Test
    public void test() {
        new Solution1().permute(new int[]{1, 2, 3}).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = null;


        public List<List<Integer>> permute(int[] nums) {
            used  = new boolean[nums.length];
            backTracking(nums);
            return res;
        }

        public void backTracking(int[] nums) {

            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i] == true) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                backTracking(nums);
                used[i] = false;
                path.remove(path.size() - 1);
            }

        }


    }

    class Solution1 {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();


        public List<List<Integer>> permute(int[] nums) {
            backTracking(nums);
            return res;
        }

        public void backTracking(int[] nums) {

            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (path.contains(nums[i])) {
                    continue;
                }
                path.add(nums[i]);
                backTracking(nums);
                path.remove(path.size() - 1);
            }

        }


    }
}
