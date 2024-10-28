package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/6 23:57
 * @注释
 */
public class LeetCode39 {

    @Test
    public void test() {
        new Solution().combinationSum(new int[]{2, 3, 6, 7}, 7).forEach(
                list -> {
                    list.forEach(System.out::print);
                    System.out.println();
                }
        );
    }


    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            for (int i = index; i < candidates.length ; i++) {
                path.add(candidates[i]);
                backTracking(candidates, i, need - candidates[i]);
                path.remove(path.size() - 1);
            }
        }

    }
}
