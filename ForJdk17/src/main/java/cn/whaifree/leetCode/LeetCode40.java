package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/6 23:57
 * @注释
 */
public class LeetCode40 {

    @Test
    public void test() {
        new Solution().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8).forEach(
                list -> {
                    list.forEach(System.out::print);
                    System.out.println();
                }
        );
    }


    class Solution {

        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used;

        /**
         * 结果不能重复！！！！
         * 1. 排序candidates
         * 2. 使用used[]数组记录使用
         * 3. 一旦candi[index]==cnadi[index-1]&&user[index]=true，那么就是用过的，不添加
         * @param candidates
         * @param target
         * @return
         */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            used = new boolean[candidates.length];
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

                // 取第一个数先 used放1，再放0；
                // 取第二个数时，如果can两个数相同，并且used为0，表示前一个数用过。
                // 如果 used为1，表示还没从递归中出来，即是【1，1】中的第二个1，是需要的
                if (i > 0 && candidates[i] == candidates[i - 1] && used[i - 1] == false) {
                    continue;
                }

                path.add(candidates[i]);
                used[i] = true;
                backTracking(candidates, i+1, need - candidates[i]);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }

    }
}
