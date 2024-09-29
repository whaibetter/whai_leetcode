package cn.whaifree.test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/6/26 20:03
 * @注释
 */
public class Review {

    public static void main(String[] args) {
        // 输入数组
        // 找到整形数组 中 超过 整形数组中超过
        // 1 1 1 2 3
        int[] ints = new int[]{1, 1, 1, 1, 5, 6, 7, 2, 1};
        System.out.println(method(ints));
    }

    public static Set<Integer> method(int[] ints) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> res = new HashSet<>();
        for (int anInt : ints) {
            Integer orDefault = map.getOrDefault(anInt, 0) + 1;
            map.put(anInt, orDefault);
            if (orDefault > ints.length / 2) {
                res.add(anInt);
            }
        }
        return res;
    }
}
