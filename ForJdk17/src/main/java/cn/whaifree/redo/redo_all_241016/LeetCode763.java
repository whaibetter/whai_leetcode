package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 17:10
 * @注释
 */
public class LeetCode763
{

    @Test
    public void test() {
        String s = "ababcbacadefegdehijhklij";
        List<Integer> ans = new Solution().partitionLabels(s);
        System.out.println(ans);
    }

    class Solution {
        public List<Integer> partitionLabels(String s) {
            Map<Character, Integer> lastIndex = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                lastIndex.put(s.charAt(i), i);
            }
            List<Integer> ans = new ArrayList<>();
            // 在便利的过程中获取最远处
            char[] charArray = s.toCharArray();
            int left = 0;
            int most = 0;
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                most = Math.max(most, lastIndex.get(c));
                if (most == i) {
                    ans.add(i - left + 1);
                    left = i + 1;
                }
            }
            return ans;
        }
    }
}
