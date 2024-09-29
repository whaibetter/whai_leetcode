package cn.whaifree.redo.redo.redo_24_3_9;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/9 13:52
 * @注释
 */
public class LeetCode738 {

    @Test
    public void test() {
        System.out.println(new Solution().monotoneIncreasingDigits(668841));
    }
    class Solution {
        public int monotoneIncreasingDigits(int n) {

            // 从右往左 找到 最后一个 递减的前后两个数
            // 前一个数-1，后面全部用9替换

            char[] c = String.valueOf(n).toCharArray();
            int target = Integer.MAX_VALUE;
            for (int i = c.length - 1; i > 0; i--) {
                if (c[i] < c[i - 1]) {
                    // 注意一定要在这对c[i]--,668841 等第二个8变成了7，就又不是递增了，所以不能简单地找到前后两个递减就结束
                    c[i - 1]--;
                    target = i;
                }
            }


            for (int i = target; i < c.length; i++) {
                c[i] = '9';
            }



            return Integer.valueOf(new String(c));
        }
    }

}
