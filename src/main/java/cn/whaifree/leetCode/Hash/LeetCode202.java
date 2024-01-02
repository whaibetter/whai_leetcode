package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 */
public class LeetCode202 {
    @Test
    public void test() {
        System.out.println(new Solution1().isHappy(2));
    }

    class Solution {

        Set<Integer> set = new HashSet<>();

        /**
         * 计算的结果是否会重复出现，如果出现就证明会进入循环
         * @param n
         * @return
         */
        public boolean isHappy(int n) {
            char[] chars = String.valueOf(n).toCharArray();
            int ans = 0;

//            StringBuilder stringBuilder = new StringBuilder("$");

            for (char aChar : chars) {
                aChar -= 48;
                ans += aChar * aChar;

//                stringBuilder.append("+" + (int)aChar + "^2");
            }

//            stringBuilder.append("=" + ans + "$");
//            System.out.println(stringBuilder.toString());


            if (ans == 1) {
                return true;
            } else if (set.contains(ans)) {
                return false;
            } else {
                set.add(ans);
                return isHappy(ans);
            }
        }
    }

    class Solution1 {


        /**
         * 计算的结果是否会重复出现，如果出现就证明会进入循环
         * @param n
         * @return
         */
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (n != 1) {
                n = getNextNumber(n);
                if (set.contains(n)) {
                    return false;
                } else {
                    set.add(n);
                }
            }
            return true;
        }

        public int getNextNumber(int n) {
            int res = 0;
            // 获取每个位
            while (n > 0) {
                int i = n % 10;
                res += i * i;
                n /= 10;
            }
            return res;
        }


    }
}
