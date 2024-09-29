package cn.whaifree.leetCode.Array;

import org.junit.Test;

/**
 * 69. x 的平方根
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 *
 * 提示：
 *
 * 0 <= x <= 231 - 1
 */
public class LeetCode69 {


    /**
     * 二分法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0;
        int right = x;
        int ans = 0;
        while (left <= right) {
            int middle = (left + right) / 2;
            // 防止过长
            if ((long) middle * middle <= x) {
                // 真正的x只会小于等于x
                ans = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return ans;
    }

    /**
     * 数学法
     *      x^(1/2) = e^((lnx)/2)
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        // 获取到的是>=正确的开方根
        double log = Math.log(x);
        int ans = (int) Math.exp(0.5 * log);
        if ((long) (ans + 1) * (ans + 1) <= x) {
            return ans + 1;
        }
        return ans;
    }

    @Test
    public void testSearch1() {
        for (int i = 0; i < 100; i++) {
            mySqrt1(i);
        }
    }
}
