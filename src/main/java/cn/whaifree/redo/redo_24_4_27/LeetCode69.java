package cn.whaifree.redo.redo_24_4_27;

import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/27 13:20
 * @注释
 */
public class LeetCode69 {
    @Test
    public void test()
    {
        Solution solution = new Solution();

        new CopyOnWriteArrayList<>().add(1);
        solution.mySqrt(9);
        for (int i = 0; i < 20; i++) {
            System.out.println("i:" + i + " " + solution.mySqrt(i));
        }
    }

    class Solution {
        public int mySqrt(int x) {
            int left = 0;
            int right = x;
            int ans = 0;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if ((long) mid * mid > x) {
                    right = mid - 1;
                } else {
                    ans = mid;
                    left = mid + 1;
                }
            }
            return ans;
        }

    }
}
