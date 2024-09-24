package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/24 12:14
 * @注释
 */
public class LeetCode40 {

    @Test
    public void test() {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum2(candidates, target);
        System.out.println(lists);
    }

    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int nowSum = 0;
        boolean[] used;
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            used = new boolean[candidates.length];
            Arrays.sort(candidates);
            backTracking(candidates, 0, target);
            return res;
        }

        public void backTracking(int[] candidates, int start, int target) {
            if (target == nowSum) {
                res.add(new ArrayList<>(path));
                return;
            }
            if (start > candidates.length) {
                return;
            }


            for (int i = start; i < candidates.length; i++) {
                if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
                    continue;
                }
                if (nowSum > target) {
                    return;
                }
                used[i] = true;
                path.add(candidates[i]);
                nowSum += candidates[i];
                backTracking(candidates, i + 1, target);
                nowSum -= candidates[i];
                path.remove(path.size() - 1);
                used[i] = false;
            }

        }
    }
}
