package cn.whaifree.interview.qz.zzyc;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/20 0:35
 * @注释
 */
public class p1 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(0, 11, 12, 13,1);
        System.out.println(new p1().isStraight(new ArrayList<>(list)));

    }

    /**
     * <img src="https://pic.leetcode-cn.com/1599885716-MGMODX-Picture1.png"/>
     * 1. 排序
     *
     *
     * 给定的5张牌是否是顺子
     * @param nums int整型ArrayList 扑克牌对应的数字集合
     * @return bool布尔型
     */
    public boolean isStraight (ArrayList<Integer> nums) {
        // write code here

        Collections.sort(nums);


        if (inEdge(nums)) { // 如果出现1 10 11 12 13 这种情况
            return true;
        }

        int joker = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i) == 0) {
                joker++;
            } else if (nums.get(i).equals(nums.get(i + 1))) {
                return false; // 排序后出现重复，不可能是顺子
            }
        }

        // 比如 5 4 3 2 1 最大的减去最小在范围内
        return nums.get(4) - nums.get(joker + 1) < 5;
    }


    static ArrayList<ArrayList<Integer>> o = new ArrayList<>();
    static {
        o.add(new ArrayList<>(Arrays.asList(0, 11, 12, 13, 1)));
        o.add(new ArrayList<>(Arrays.asList(0, 10, 12, 13, 1)));
        o.add(new ArrayList<>(Arrays.asList(0, 10, 11, 13, 1)));
        o.add(new ArrayList<>(Arrays.asList(0, 10, 11, 12, 1)));
        o.add(new ArrayList<>(Arrays.asList(0, 10, 11, 12, 13)));
        o.add(new ArrayList<>(Arrays.asList(1, 10, 11, 12, 13)));
    }
    public static boolean inEdge(ArrayList<Integer> list) {
        for (ArrayList<Integer> integers : o) {
            if (integers.equals(list)) {
                return true;
            }
        }
        return false;
    }

    class Solution {
        public boolean checkDynasty(int[] places) {
            Set<Integer> repeat = new HashSet<>();
            int max = 0, min = 14;
            for(int place : places) {
                if(place == 0) continue; // 跳过未知朝代
                max = Math.max(max, place); // 最大编号朝代
                min = Math.min(min, place); // 最小编号朝代
                if(repeat.contains(place)) {
                    return false; // 若有重复，提前返回 false
                }
                repeat.add(place); // 添加此朝代至 Set
            }
            return max - min < 5; // 最大编号朝代 - 最小编号朝代 < 5 则连续
        }
    }

}


class P{

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 6, 6, 11);

        System.out.println(prize(new ArrayList<>(list), 12));
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param res int整型ArrayList
     * @param target int整型
     * @return int整型
     */
    public static int prize (ArrayList<Integer> res, int target) {
        // write code here

//        for (int i = 0; i < res.size(); i++) {
//            if (res.get(i) >= target) {
//                return i;
//            }
//        }


        int left = 0;
        int right = res.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (res.get(mid) > target) {
                right = mid - 1;
            } else if (res.get(mid) == target) {
                return mid + 1;
            }else {
                left = mid + 1;
            }
        }
        if (left < res.size() && res.get(left) > target) {
            return left + 1;
        }


        return -1;
    }
}
