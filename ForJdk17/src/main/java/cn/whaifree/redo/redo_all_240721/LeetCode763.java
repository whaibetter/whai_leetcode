package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/8 22:07
 * @注释
 */
public class LeetCode763 {

    @Test
    public void test()
    {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(new Solution().partitionLabels(s));
    }

    class Solution {
        /**
         * 获取每个byte最后出现的地方
         *
         * 挨个遍历，直到索引的地方不是最后出现的地方，就截取。
         *      每次需要找到当前比遍历中最远到哪了
         *
         * @param s
         * @return
         */
        public List<Integer> partitionLabels(String s) {
            byte[] bytes = s.getBytes();
            int[] index = new int[26];
            for (int i = 0; i < bytes.length; i++) {
                index[bytes[i] - 97] = i;
            }

            List<Integer> res = new ArrayList<>();
            int start = 0;
            int rightMax = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte aByte = bytes[i];
                int ind = index[aByte - 97];
                rightMax = Math.max(rightMax, ind); // 当前最右边界
                if (ind == i && i == rightMax) {
                    res.add(rightMax - start + 1);
                    start = rightMax + 1;
                }
            }

            return res;
        }
    }
}
