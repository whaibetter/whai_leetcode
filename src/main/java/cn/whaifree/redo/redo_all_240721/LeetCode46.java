package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/3 10:19
 * @注释
 */
public class LeetCode46 {

    @Test
    public void test() {
        int[] nums = {1,2,3};
        Solution solution = new Solution();
        solution.permute(nums).forEach(
                System.out::println
        );
    }
    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> permute(int[] nums) {
            back(nums);
            return res;
        }

        public void back(int[] nums) {
            if (path.size() >= nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (path.contains(nums[i])) {
                    continue;
                }
                path.add(nums[i]);
                back(nums);
                path.remove(path.size() - 1);
            }
        }
    }
}
