package cn.whaifree.redo.redo_all_241016;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/26 12:52
 * @注释
 */
public class LeetCode165 {

    class Solution {
        public int compareVersion(String version1, String version2) {

            int v1Index = 0;
            int v2Index = 0;
            int v1Length = version1.length();
            int v2Length = version2.length();
            while (v1Index < v1Length || v2Index < v2Length) {
                int tmpSum1 = 0;
                while (v1Index < v1Length && version1.charAt(v1Index) != '.') {
                    tmpSum1 += tmpSum1 * 10 + (version1.charAt(v1Index) - '0');
                    v1Index++;
                }
                v1Index++;
                int tmpSum2 = 0;
                while (v2Index < v2Length && version2.charAt(v2Index) != '.') {
                    tmpSum2 += tmpSum2 * 10 + (version2.charAt(v2Index) - '0');
                    v2Index++;
                }
                v2Index++;
                if (tmpSum1 != tmpSum2) {
                    return tmpSum1 - tmpSum2;
                }
            }
            return 0;
        }


    }

}
