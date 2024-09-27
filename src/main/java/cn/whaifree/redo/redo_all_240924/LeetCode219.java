package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/26 14:43
 * @注释
 */
public class LeetCode219 {

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 9, 8, 7, 1};
        int k = 3;
        System.out.println(new Solution().containsNearbyDuplicate(nums, k));
    }

    class Solution {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                List<Integer> list = map.get(nums[i]);
                for (int j = 0; j < list.size(); j++) {
                    if (Math.abs(i - list.get(j)) <= k) {
                        return true;
                    }
                }
                list.add(i);
            }
            return false;
        }
    }
}
