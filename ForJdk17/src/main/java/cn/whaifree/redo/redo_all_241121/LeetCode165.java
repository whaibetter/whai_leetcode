package cn.whaifree.redo.redo_all_241121;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/24 11:42
 * @注释
 */
public class LeetCode165 {

    @Test
    public void test() {
        String s1 = "1.0";
        String s2 = "1.0.0.0";
        System.out.println(new Solution().compareVersion(s1, s2));
    }

    class Solution {
        public int compareVersion(String version1, String version2) {
            // 双指针，遇到.要退出
            int index1 = 0;
            int index2 = 0;
            while (index1 < version1.length() || index2 < version2.length()) {

                int A = 0;
                while (index1 < version1.length() && version1.charAt(index1) != '.') {
                    A *= 10;
                    A += version1.charAt(index1) - '0';
                    index1++;
                }
                index1++;

                int B = 0;
                while (index2 < version2.length() && version2.charAt(index2) != '.') {
                    B *= 10;
                    B += version2.charAt(index2) - '0';
                    index2++;
                }
                index2++;

                if (A != B) {
                    return A > B ? 1 : -1;
                }
            }
            return 0;
        }
    }
}
