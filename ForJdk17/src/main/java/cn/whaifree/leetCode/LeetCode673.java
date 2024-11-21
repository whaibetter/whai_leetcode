package cn.whaifree.leetCode;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.IntPredicate;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/20 12:57
 * @注释
 */
public class LeetCode673 {

    @Test
    public void test() {
        // 2 2 2 2 2
        System.out.println(new Solution().findNumberOfLIS(new int[]{1}));
    }

    class Solution {
        /**
         * dp[i] 表示0-i最长递增子序列，子序列长度
         * count[i] 以nums[i]为结尾的字符串，最长递增子序列的个数为count[i]
         *
         * @param nums
         * @return
         */
        public int findNumberOfLIS(int[] nums) {
            if (nums.length <= 1) return nums.length;
            int max = 0;

            int[] dp = new int[nums.length];
            // 初始化
            Arrays.fill(dp, 1);
            int[] count = new int[nums.length];
            Arrays.fill(count, 1);
            for (int i = 1; i < nums.length; i++) {
                int left = i - 1;
                while (left >= 0) {
                    if (nums[left] < nums[i]) {

                        if (dp[left] + 1 > dp[i]) {
                            // 找到一个更长的子序列
                            // 那么数量应该为left的count，表示最长递增子序列的个数更新为left处的
                            count[i] = count[left];
                        } else if (dp[left] + 1 == dp[i]) {
                            // 如果找到的子序列长度和原来的一样，那么就加上left处的
                            // 增加left处的最长递增，因为这时最长递增子序列的长度都一样
                            count[i] += count[left];
                        }

                        dp[i] = Math.max(dp[i], dp[left] + 1); // 最长递增子序列的长度
                    }
                    max = Math.max(max, dp[i]);
                    left--;
                }

            }

            int res = 0;
            // 统计所有最长递增子序列的个数为max的
            for (int i = 0; i < nums.length; i++) {
                if (dp[i] == max) {
                    res += count[i];
                }
            }
            return res;
        }

    }
}
class Test989 {
    private int a = 10;
    int b = 20;
    static int c = 30;

    public static void main(String[] args) {
        Test989 t = new Test989();  // 创建 Test 类的对象
        System.out.println(t.a);  // 错误：a 是 private 的
        System.out.println(t.b);  // 正确：b 是实例变量
//        System.out.println(this.c);  // 正确：c 是 static 变量
    }
}
