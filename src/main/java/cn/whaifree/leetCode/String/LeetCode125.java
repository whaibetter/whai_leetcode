package cn.whaifree.leetCode.String;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/18 23:28
 * @注释
 */
public class LeetCode125 {
    public static void main(String[] args) {
        System.out.println((int) 'A');
        System.out.println((int) 'Z');
        System.out.println((int) 'a');
        System.out.println((int) 'z');
        System.out.println(new Solution().isPalindrome("A man, a plan, a canal: Panama"));

    }

    static class Solution {
        public boolean isPalindrome(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                // 大写字符转换为小写字符、并移除所有非字母数字字符
//                if (c >= 'A' && c <= 'Z') {
//                    sb.append((char) (c + 32));
//                } else if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
//                    sb.append(c);
//                }
                if (Character.isLetterOrDigit(c)) {
                    sb.append(Character.toLowerCase(c));
                }

            }
            return isHuiWen(sb.toString());
        }
        public boolean isHuiWen(String s) {
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
