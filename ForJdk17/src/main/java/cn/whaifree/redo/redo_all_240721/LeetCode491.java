package cn.whaifree.redo.redo_all_240721;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/2 23:57
 * @注释
 */
public class LeetCode491 {

    public static void main(String[] args) {
        new Solution().findSubsequences(new int[]{4, 6, 7, 7}).forEach(System.out::println);
//        new Solution1().subsetsWithDup(new int[]{1,2,2}).forEach(System.out::println);
    }

    static class Solution {
        List<List<Integer>> res;
        List<Integer> path;
        public List<List<Integer>> findSubsequences(int[] nums) {
            res = new ArrayList<>();
            path = new ArrayList<>();
            back(0, nums);
            return res;
        }

        /**
         * 4677
         *   4
         *   6
         *  7  7  这两个要去重，在这层用set
         *
         *
         *
         * @param start
         * @param nums
         */
        public void back(int start, int[] nums) {
            if (path.size() > 1) {
                res.add(new ArrayList<>(path));
            }
            if (start >= nums.length) {
                return;
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    continue;
                }
                if (path.isEmpty() || path.get(path.size() - 1) <= nums[i]) {
                    path.add(nums[i]);
                    set.add(nums[i]);
                    back(i + 1, nums);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
// 错误，used只能用在相邻的判断
//    static class Solution2 {
//        List<List<Integer>> res;
//        List<Integer> path;
//        boolean[] used = null;
//        public List<List<Integer>> findSubsequences(int[] nums) {
//            res = new ArrayList<>();
//            path = new ArrayList<>();
//            used = new boolean[nums.length];
//            back(0, nums);
//            return res;
//        }
//
//        /**
//         * 4677
//         *   4
//         *   6
//         *  7  7  这两个要去重，在这层用set
//         *
//         *
//         *
//         * @param start
//         * @param nums
//         */
//        public void back(int start, int[] nums) {
//            if (path.size() > 1) {
//                res.add(new ArrayList<>(path));
//            }
//            if (start >= nums.length) {
//                return;
//            }
//            for (int i = start; i < nums.length; i++) {
//                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
//                    continue;
//                }
//                if (path.isEmpty() || path.get(path.size() - 1) <= nums[i]) {
//                    path.add(nums[i]);
//                    used[i] = true;
//                    back(i + 1, nums);
//                    used[i] = false;
//                    path.remove(path.size() - 1);
//                }
//            }
//        }
//    }


    static class Solution1 {

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

                // u1 u2 相同，并且u1曾经被调用过，则不继续进行本次循环
//                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
//                    continue;
//                }

                temp.add(nums[i]);
                used[i] = true;
                backTracking(nums,i+1);
                temp.remove(temp.size() - 1);
                used[i] = false;
            }

        }
    }
}
