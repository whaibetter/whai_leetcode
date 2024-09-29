package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/18 13:26
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
        List<Integer> temp = new ArrayList<>();
        boolean[] used = null;
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            used = new boolean[nums.length];
            backTracking(nums, 0);
            return res;
        }

        void backTracking(int[] nums, int start) {
            res.add(new ArrayList<>(temp));
            if (start >= nums.length) {
                return;
            }



            for (int i = start; i < nums.length ; i++) {

                // 1. u1 u2 相同，并且u1曾经被调用过，则不继续进行本次循环
//                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
//                    continue;
//                }
                // 2. 不使用used数组，跳过当前树层使用过的、相同的元素
                if ( i > start && nums[i - 1] == nums[i] ) {
                    continue;
                }

                temp.add(nums[i]);
                used[i] = true;
                backTracking(nums,i+1);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }

        }
    }
}
