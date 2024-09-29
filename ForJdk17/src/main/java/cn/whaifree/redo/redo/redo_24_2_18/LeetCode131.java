package cn.whaifree.redo.redo.redo_24_2_18;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/18 9:04
 * @注释
 */
public class LeetCode131 {

    @Test

    public void test() {
        String abc = "aab";
        new Solution().partition(abc).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    class Solution {

        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();


        public List<List<String>> partition(String s) {
            backTracing(s,0);
            return res;
        }

        public void backTracing(String s,int start) {

            // 遍历完了，树到根部了，就记为一次结果
            if (start >= s.length()) {
                res.add(new ArrayList<>(list));
            }

            for (int i = start; i < s.length(); i++) {
                // 只有当是回文串时，才进行下一次切割
                if (isHuiWen(s, start, i)) {
                    String substring = s.substring(start, i + 1);
                    list.add(substring);
                    backTracing(s, i + 1);
                    list.remove(list.size() - 1);
                }
            }
        }

        public boolean isHuiWen(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start) != s.charAt(end)) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }
}
