package cn.whaifree.redo.redo_all_240721;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/7/22 23:18
 * @注释
 */
public class LeetCode202 {
    public static void main(String[] args)
    {
        int n = 2;
        System.out.println(isHappy(n));
        System.out.println(isHappy(19));
    }

    /**
     * 2 4 16 1+36=37 9+47=56 25+36=61 36+1=37
     * @param n
     * @return
     */
    public static boolean isHappy(int n)
    {
        Set<Integer> set = new HashSet<>();

        while (n != 1) {
            int tmpSum = 0;
            while (n > 0) {
                int retail = n % 10;
                tmpSum += retail * retail;
                n /= 10;
            }
            if (set.contains(tmpSum)) {
                return false;
            }
            set.add(tmpSum);
            n = tmpSum;
        }
        return true;


    }
}
