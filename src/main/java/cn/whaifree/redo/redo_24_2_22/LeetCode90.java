package cn.whaifree.redo.redo_24_2_22;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/23 10:02
 * @注释
 */
public class LeetCode90 {
    @Test
    public void test() {
        new Solution().subsetsWithDup(new int[]{1, 2, 2}).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = null;
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            used = new boolean[nums.length];
            Arrays.sort(nums);
            backTracking(nums, 0);
            return res;
        }

        public void backTracking(int[] nums, int start) {
            res.add(new ArrayList<>(path));
            if (start >= nums.length) {
                return;
            }

            for (int i = start; i < nums.length; i++) {
                // 兄弟节点被使用完了，就是false，continue
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                    continue;
                }

                used[i] = true;
                path.add(nums[i]);
                backTracking(nums, i + 1);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}
