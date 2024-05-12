package cn.whaifree.redo.redo.redo_24_2_22;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/23 12:10
 * @注释
 */
public class LeetCode491 {

    @Test
    public void test() {
        new Solution().findSubsequences(new int[]{1, 5, 3, 3}).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }


    class Solution {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracking(nums, 0);
            return res;
        }


        public void backTracking(int[] nums, int start) {
            if (path.size() > 1) {
                // 插入res
                res.add(new ArrayList<>(path));
            }

            // 每层跳过，不能排序，所以不能使用used
            Set<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                // 如果 递减，跳过
                if ((!path.isEmpty() && path.getLast() > nums[i]) || set.contains(nums[i])) {
                    continue;
                }

                set.add(nums[i]);
                path.add(nums[i]);
                backTracking(nums, i + 1);
                path.removeLast();
            }


        }
    }


}
