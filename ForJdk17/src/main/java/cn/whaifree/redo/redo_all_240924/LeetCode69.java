package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/1 13:01
 * @注释
 */
public class LeetCode69 {

    @Test
    public void test() {
        Solution solution = new Solution();
        int x = 8;
        int res = solution.mySqrt(x);
        System.out.println(res);
    }

    class Solution {
        public int mySqrt(int x) {
            int left = 0;
            int right = x;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long pow = (long) mid * mid;
                if (pow == x) {
                    return mid;
                } else if (pow > x) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return right; // 最后left必然在right右边，left = right+1
        }
    }
}
