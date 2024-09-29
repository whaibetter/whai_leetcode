package cn.whaifree.redo.redoAll;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 13:51
 * @注释
 */
public class LeetCode151 {
    @Test
    public void test()
    {
        Solution solution = new Solution();
        String s = "  hello  ";
        String s1 = solution.reverseWords(s);
        System.out.println(s1);
    }

    class Solution {
        public String reverseWords(String s) {
            // s = "  hello world  "
            // 去除前后空白串
            s = s.trim();
            String[] split = s.split("\\s+"); // 活着 " +"
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = split.length - 1; i > 0; i--) {
                stringBuilder.append(split[i]).append(" ");
            }
            stringBuilder.append(split[0]);
            return stringBuilder.toString();
        }
    }
}
