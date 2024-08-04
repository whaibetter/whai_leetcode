package cn.whaifree.redo.redo_all_240721;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/29 21:53
 * @注释
 */

public class LeetCode40 {
    public static void main(String[] args) {
        new Solution().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8).forEach(list -> {
            System.out.println(list);
        });
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int nowSum = 0;
    boolean[] used = null;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        used = new boolean[candidates.length];
        back(candidates, target, 0);
        return res;
    }

    public void back(int[] candidates, int target, int start) {
        if (nowSum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (nowSum > target) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
                continue;
            }
            path.add(candidates[i]);
            nowSum += candidates[i];
            used[i] = true; // 这个被使用了
            back(candidates, target, i + 1);
            used[i] = false;
            path.remove(path.size() - 1);
            nowSum -= candidates[i];
        }
    }


    static class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        /**
         *
         *
         * @param candidates
         * @param target
         * @return
         */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            backTracking(candidates, 0, target);
            return res;
        }

        public void backTracking(int[] candidates, int index, int need) {
            if (need < 0) {
                return;
            }
            if (need == 0) {
                res.add(new ArrayList<>(path));
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = index; i < candidates.length; i++) {
                if (set.contains(candidates[i])) {
                    continue;
                }
                set.add(candidates[i]);
                path.add(candidates[i]);
                backTracking(candidates, i + 1, need - candidates[i]);
                path.remove(path.size() - 1);
            }
        }

    }

}
