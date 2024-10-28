package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/18 9:27
 * @注释
 */
public class LeetCode93 {

    @Test
    public void test() {
        for (String restoreIpAddress : new Solution1().restoreIpAddresses("101023")) {
            System.out.println(restoreIpAddress);
        }
    }

    class Solution {

        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        public List<String> restoreIpAddresses(String s) {
            backTracking(s, 0, 0);
            return res;
        }

        /**
         *
         * @param s
         * @param start 递归开始标记
         * @param number 这是第几个切片，4个切片为一组有效返回
         */
        public void backTracking(String s, int start,int number) {
            // 如果 第5个切片了，则存在多余的字符，直接return
            if (number > 4) {
                return;
            }

            if (start >= s.length() && path.size() == 4) {
                res.add(String.join(".", path));

//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < path.size() - 1; i++) {
//                    stringBuilder.append(path.get(i)).append(".");
//                }
//                stringBuilder.append(path.get(path.size() - 1));
//                res.add(stringBuilder.toString());
                return;
            }




            for (int i = start; i < s.length() ; i++) {

                if (isValid(s, start, i + 1)) {

                    path.add(s.substring(start, i + 1));
                    backTracking(s, i + 1, number + 1);
                    path.remove(path.size() - 1);
                }else {
                    // 2565 256无效，那么2565直接就无效了
                    break;
                }
            }
        }

        public boolean isValid(String s, int start, int end) {
            String substring = s.substring(start, end);
            // 子串最多不超过3个字符
            if (substring.length() > 3 ) {
                return false;
            }
            // “01”为非法，"0"为合法
            if (substring.length() != 1 && substring.startsWith("0")) {
                return false;
            }
            int integer = Integer.parseInt(substring);
            if (integer >= 0 && integer <= 255) {
                return true;
            }
            return false;
        }

        /**
         * 通过char进行字符串转为int
         * @param s
         * @param start
         * @param end
         * @return
         */
        private boolean isValid(StringBuilder s, int start, int end){
            if(start > end)
                return false;
            if(s.charAt(start) == '0' && start != end)
                return false;
            int num = 0;
            for(int i = start; i <= end; i++){
                int digit = s.charAt(i) - '0';
                num = num * 10 + digit;
                if(num > 255)
                    return false;
            }
            return true;
        }
    }
    class Solution1 {

        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        public List<String> restoreIpAddresses(String s) {
            backTracking(s, 0);
            return res;
        }

        /**
         *
         * @param s
         * @param start 递归开始标记
         */
        public void backTracking(String s, int start) {

            if (start >= s.length() && path.size() == 4) {
                res.add(String.join(".", path));

//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < path.size() - 1; i++) {
//                    stringBuilder.append(path.get(i)).append(".");
//                }
//                stringBuilder.append(path.get(path.size() - 1));
//                res.add(stringBuilder.toString());
                return;
            }




            for (int i = start; i < s.length() ; i++) {

                if (isValid(s, start, i + 1)) {

                    path.add(s.substring(start, i + 1));
                    backTracking(s, i + 1);
                    path.remove(path.size() - 1);
                }else {
                    // 2565 256无效，那么2565直接就无效了
                    break;
                }
            }
        }

        public boolean isValid(String s, int start, int end) {
            String substring = s.substring(start, end);
            // 子串最多不超过3个字符
            if (substring.length() > 3 ) {
                return false;
            }
            // “01”为非法，"0"为合法
            if (substring.length() != 1 && substring.startsWith("0")) {
                return false;
            }
            int integer = Integer.parseInt(substring);
            if (integer >= 0 && integer <= 255) {
                return true;
            }
            return false;
        }

    }

}
