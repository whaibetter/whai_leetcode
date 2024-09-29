package cn.whaifree.leetCode.BackTracking;

import cn.whaifree.leetCode.model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/19 16:41
 * @注释
 */
public class LeetCode47 {

    @Test
    public void test(){
        new Solution1().permuteUnique(new int[]{1, 1, 2}).forEach(
                list -> {
                    System.out.println(list);

                }
        );
    }

    /**
     * 使用层的set+路径的bool[]
     */
    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = null;
        /**
         *     给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
         *     相比46题，不重复
         * @param nums
         * @return
         */
        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];

            backTracking(nums);

            return res;
        }

        public void backTracking(int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            // 这个set用来对每层进行过滤，每层不能重复
            // used对每个路径进行过滤，没个路径不会出现同一个index，但有可能多个index都是同一个元素
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (used[i] == true || set.contains(nums[i])) {
                    continue;
                }
                set.add(nums[i]);
                used[i] = true;
                path.add(nums[i]);
                backTracking(nums);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 使用排序+used
     */
    class Solution1 {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = null;
        /**
         *     给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
         *     相比46题，不重复
         * @param nums
         * @return
         */
        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            Arrays.sort(nums);

            backTracking(nums);
            return res;
        }

        public void backTracking(int[] nums) {
            if (path.size() == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }


            for (int i = 0; i < nums.length; i++) {
                // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
                // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
                // 一层
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                    continue;
                }
                // used[i] == true，说明同⼀树⽀nums[i]使⽤过
                // 这个路径上被使用过
                // 一条路径
                if (used[i]) {
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


}
