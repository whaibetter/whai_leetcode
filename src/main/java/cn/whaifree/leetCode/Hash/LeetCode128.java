package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 13:53
 * @注释
 */
public class LeetCode128 {

    @Test
    public void test() {
        int[] nums = {4, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }


        int longest = 0;
        // ON
        for (int num : set) {

            // 这里是关键，说明这个元素i是某一组排序的开头，不存在i-1的元素
            if (!set.contains(num-1)){
                int len = 1;
                while (set.contains(++num)) {
                    len++;
                }
                longest = Math.max(longest, len);
            }

        }
        return longest;
    }
}
