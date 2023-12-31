package cn.whaifree.leetCode.Array;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * 完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。
 * 不能使用任何内置的库函数，如  sqrt 。
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 解释：返回 true ，因为 4 * 4 = 16 且 4 是一个整数。
 *
 *
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 * 解释：返回 false ，因为 3.742 * 3.742 = 14 但 3.742 不是一个整数。
 *
 * 提示：
 * 1 <= num <= 231 - 1
 */
public class LeetCode367 {

    /**
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int left = 0;
        int right = num;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (middle * middle == num) {
                return true;
            } else if ((long) middle * middle > num) {
                right = middle - 1;
//                rigjt--;
            } else {
                // 如果middle * middle < num ，那么 寻找的区间就应该是 xx,middle) 或者说middle*middle 这个数太大了
//                left++;
                left = middle + 1;
            }
        }
        return false;
    }


    /**
     * 代码中使用的 pow\texttt{pow}pow 函数的时空复杂度与 CPU 支持的指令集相关，这里不深入分析。
     * @param num
     * @return
     */
    public boolean isPerfectSquare1(int num) {
        double sqrt = Math.sqrt(num);
        // 判断有没有小数
        if (sqrt == (int) sqrt) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 时间复杂度：On
     * 空间复杂 O1
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num) {
        // 暴力
        for (int i = 1; i <= num; i++) {
            if ((long)i * i == num) {
                return true;
            }
        }
        return false;
    }




    @Test
    public void main() {
        LeetCode367 solution = new LeetCode367();

        // Test case 1: num is a perfect square
        int num1 = 16;
        assertTrue(solution.isPerfectSquare1(num1));

        // Test case 2: num is not a perfect square
        int num2 = 15;
        assertFalse(solution.isPerfectSquare1(num2));

    }
}


