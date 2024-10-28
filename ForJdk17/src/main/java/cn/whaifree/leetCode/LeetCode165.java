package cn.whaifree.leetCode.String;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/31 14:57
 * @注释
 */
public class LeetCode165 {

   @Test
   public void test() {
       Solution2 solution = new Solution2();
       int result = solution.compareVersion("1.01.2", "1.001");
       System.out.println(result);
   }

    class Solution2 {
        /**
         * 双指针，不断计算有效长度
         * @param version1
         * @param version2
         * @return
         */
        public int compareVersion(String version1, String version2) {

            int v1Index = 0;
            int v2Index = 0;
            while (v1Index < version1.length() || v2Index < version2.length()) {
                int tmpV1Sum = 0;
                while (v1Index < version1.length() && version1.charAt(v1Index) != '.') {
                    tmpV1Sum += tmpV1Sum * 10 + version1.charAt(v1Index) - '0';
                    v1Index++;
                }
                v1Index++; // 跳过.

                int tmpV2Sum = 0;
                while (v2Index < version2.length() && version2.charAt(v2Index) != '.') {
                    tmpV2Sum += tmpV2Sum * 10 + version2.charAt(v2Index) - '0';
                    v2Index++;
                }
                v2Index++; // 跳过.
                if (tmpV1Sum < tmpV2Sum) {
                    return -1;
                } else if (tmpV1Sum > tmpV2Sum) {
                    return 1;
                }
            }
            return 0;
        }
    }

    class Solution {
        /**
         * 1.2 1.10
         * 1.01 1.001
         * 1.0.0.0
         *
         *
         * @param version1
         * @param version2
         * @return
         */
        public int compareVersion(String version1, String version2) {
            String[] splitV1 = version1.split("\\.");
            String[] splitV2 = version2.split("\\.");
            if (splitV1.length < splitV2.length) {
                splitV1 = fill(splitV1, splitV2.length);
            }else {
                splitV2 = fill(splitV2, splitV1.length);
            }
            // 现在两边一样长了

            for (int i = 0; i < splitV1.length; i++) {
                Integer v1 = Integer.valueOf(splitV1[i]);
                Integer v2 = Integer.valueOf(splitV2[i]);
                if (v1 > v2) {
                    return 1;
                } else if (v1 < v2) {
                    return -1;
                }
            }

            return 0;


        }

        public String[] fill(String[] split, int newLen) {
            String[] tmp = new String[newLen];
            int i = 0;
            for (; i < split.length; i++) {
                tmp[i] = split[i];
            }
            for (; i < tmp.length; i++) {
                tmp[i] = "0";
            }
            return tmp;
        }
    }
}
