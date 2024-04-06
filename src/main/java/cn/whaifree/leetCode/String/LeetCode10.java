package cn.whaifree.leetCode.String;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/5 11:36
 * @注释
 */
public class LeetCode10 {

    class Solution {
        public boolean isMatch(String s, String p) {

            char[] sChar = s.toCharArray();
            char[] pChar = p.toCharArray();
            /**
             * dp[sIndex][pIndex]表示s字符串的前sIndex个字符是否和p字符串的前pIndex个字符完成正则表达式的匹配
             *
             * 当p字符串的第pIndex个字符不为*时
             *      if p第pIndex个字符与s的第sIndex个字符匹配
             *      dp[sIndex][pIndex] =
             *           f[sIndex - 1][pIndex - 1](即：s的前sIndex - 1个字符和p的前pIndex-1个字符的匹配结果)。
             *      如果p第pIndex个字符与s的第sIndex个字符不匹配，
             *            则结果为false；
             * 当p字符串的第pIndex个字符为*时
             *      此时*前面的符号可以出现0次或任意次
             *          - x出现0次
             *              此时结果为f[sIndex][pIndex - 2]。此时相当于删除了*以及x
             *          - x出现非0的任意次， 此时的结果为f[sIndex - 1][pIndex] && (p.charAt(pIndex - 2) == '.' || p.charAt(pIndex - 2) == s.charAt(sIndex - 1));
             *
             */


            return false;

        }
    }

}
