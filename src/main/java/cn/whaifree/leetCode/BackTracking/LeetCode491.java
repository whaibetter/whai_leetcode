package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/18 16:41
 * @注释
 */
public class LeetCode491 {

    @Test
    public void test() {
        new Solution().findSubsequences(new int[]{4,6,7,5,7}).forEach(list -> {
            System.out.println(list);
        });
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> findSubsequences(int[] nums) {

            backTracking(nums, 0);
            return res;
        }

        public void backTracking(int[] nums, int index) {

            if (path.size() > 1) {
                // 插入res
                res.add(new ArrayList<>(path));
            }

            Set<Integer> set = new HashSet<>();
            for (int i = index; i < nums.length; i++) {
                // 如果不满足非递减，即递增，这个子树不保留
                if ((!path.isEmpty() && path.get(path.size() - 1) > nums[i]) // 递增
                    || set.contains(nums[i])   // 如[4,7,xx,7],[4,7]已经出现在path中，7加入了set，那么在本层后面遇到7的时候，就直接continues
                ) {
                    continue;
                }
                set.add(nums[i]);
                path.add(nums[i]);
                backTracking(nums, i + 1);
                path.remove(path.size() - 1);
            }

        }
    }

}
