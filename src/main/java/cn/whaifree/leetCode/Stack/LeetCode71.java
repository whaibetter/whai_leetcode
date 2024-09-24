package cn.whaifree.leetCode.Stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 15:29
 * @注释
 */
public class LeetCode71 {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<int[]> res = new ArrayList<>();
                while (true) {
                    System.out.println("ADD");
                    res.add(new int[100000]);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<int[]> res = new ArrayList<>();
                while (true) {
                    System.out.println(1000);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();

    }

    @Test
    public void test() {
        System.out.println(new Solution().simplifyPath("/.../../.."));
    }

    class Solution {
        /**
         *
         * . 当前目录
         * .. 父亲目录
         * ///*n /// // / 变为/
         *
         *
         * - 碰到.. 出栈
         * - 碰到. 忽略
         * - N*\// 都忽略
         *
         *
         * @param path
         * @return
         */
        public String simplifyPath(String path) {
            Deque<String> stack = new LinkedList<>();
            String[] split = path.split("/");
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (s.equals("/")||s.isEmpty()||s.equals(".")) {
                    continue;
                } else if (s.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    continue;
                }
                stack.push(s);
            }
            StringBuilder stringBuilder = new StringBuilder();
            while (!stack.isEmpty()) {
                stringBuilder.append("/");
                stringBuilder.append(stack.pollLast());
            }
            return stringBuilder.isEmpty() ? "/" : stringBuilder.toString();
        }
    }
}
