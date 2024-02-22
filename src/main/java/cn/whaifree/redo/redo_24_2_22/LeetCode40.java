package cn.whaifree.redo.redo_24_2_22;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/22 13:26
 * @注释
 */
public class LeetCode40 {

    @Test
    public void test() {
        new Solution().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }
    class Solution {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used = null;
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            used = new boolean[candidates.length];
            backTracking(candidates, target, 0);
            return res;
        }

        public void backTracking(int[] candidates, int retailNeed,int start) {
            if (0 == retailNeed ) {
                res.add(new ArrayList<>(path));
                return;
            }
            if (retailNeed < 0) {
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                // 如果前后两个相同，前一个false表示已经使用完了，再使用就会重复
                if (i > 0 && candidates[i - 1] == candidates[i] && used[i - 1] == false) {
                    continue;
                }
                path.add(candidates[i]);
                used[i] = true;
                backTracking(candidates, retailNeed - candidates[i], i + 1);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
