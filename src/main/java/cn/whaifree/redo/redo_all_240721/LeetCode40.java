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
        new LeetCode40().combinationSum2(new int[]{14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12}, 27).forEach(
                list -> {
                    list.forEach(
                            integer -> System.out.print(integer + " ")
                    );
                    System.out.println();
                }
        );
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
}
