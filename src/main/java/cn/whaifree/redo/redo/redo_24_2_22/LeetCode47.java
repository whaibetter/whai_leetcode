package cn.whaifree.redo.redo.redo_24_2_22;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/23 13:43
 * @注释
 */
public class LeetCode47 {
    @Test
    public void test() {
        new Solution().permuteUnique(new int[]{1,1,2}).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = null;
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            used = new boolean[nums.length];
            backTracking(nums);
            return res;
        }

        public void backTracking(int[] nums) {

            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if ((i > 0 && nums[i] == nums[i - 1] && used[i-1] == false)) {
                    continue;
                }

                if (used[i]) {
                    continue;
                }
                used[i] = true;
                path.add(nums[i]);
                backTracking(nums);
                used[i] = false;
                path.removeLast();
            }

        }
    }
}
