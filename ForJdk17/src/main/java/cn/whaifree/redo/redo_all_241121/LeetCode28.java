package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/24 17:14
 * @注释
 */
public class LeetCode28 {

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.strStr("hell", "ll"));
    }

    class Solution {
        public int strStr(String haystack, String needle) {
            for (int i = 0; i <= haystack.length() - needle.length(); i++) {
                String substring = haystack.substring(i, i + needle.length());
                if (substring.equals(needle)) {
                    return i;
                }
            }
            return -1;
        }
    }

}
