package cn.whaifree.redo.redo_all_240721;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/1 21:33
 * @注释
 */
public class LeetCode90 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        LeetCode90 leetCode90 = new LeetCode90();
        System.out.println(leetCode90.subsetsWithDup(nums));
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used = null;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
        back(nums, 0);
        return res;
    }

    public void back(int[] nums, int start) {
        if (start > nums.length) {
            return;
        }
        res.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            back(nums, i + 1);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
