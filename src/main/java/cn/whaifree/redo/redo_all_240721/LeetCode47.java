package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/3 13:15
 * @注释
 */
public class LeetCode47 {
    @Test
    public void test()
    {
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> x = new Solution1().permuteUnique(nums);
        for (List<Integer> integers : x) {
            System.out.println(integers);
        }
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> permuteUnique(int[] nums) {
            back(nums);
            return res;
        }

        public void back(int[] nums) {
            if (path.size() >= nums.length) {
                res.add(path.stream().mapToInt(value -> nums[value]).boxed().toList());
                return;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (path.contains(i)) {
                    continue;
                }
                if (set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                path.add(i);
                back(nums);
                path.remove(path.size() - 1);
            }
        }
    }

    class Solution1 {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = null;
        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            Arrays.sort(nums);
            back(nums);
            return res;
        }

        public void back(int[] nums) {
            if (path.size() >= nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                    continue;
                }
                if (used[i]) { // 每条路径，同一个元素不会再使用
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                back(nums);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
