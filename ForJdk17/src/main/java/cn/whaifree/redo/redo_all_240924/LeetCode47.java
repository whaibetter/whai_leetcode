package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/13 14:32
 * @注释
 */
public class LeetCode47 {
    @Test
    public void test() {
        int[] nums = {1,1,2};
        List<List<Integer>> res = new Solution().permuteUnique(nums);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[] used = null;
        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            back(nums);
            return res;
        }

        public void back(int[] nums) {
            if (temp.size() == nums.length) {
                res.add(new ArrayList<>(temp));
                return;
            }


            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (used[i]||set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                used[i] = true;
                temp.add(nums[i]);
                back(nums);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
}
