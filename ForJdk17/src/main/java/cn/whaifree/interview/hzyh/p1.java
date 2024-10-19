package cn.whaifree.interview.hzyh;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/17 18:23
 * @注释
 */
public class p1 {


    /**
     * abcdefgihjklmnopqrstuvwxyz
     *
     * 中包含问号?
     *
     * 判断最少需要多少长可以涵盖这26个字符，?是万能的。
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}




class p2{


    public static void main(String[] args) {
        p2 p2 = new p2();
        System.out.println(p2.find_median(new float[]{1f, 3f, 7f}, new float[]{2f, 5, 10}));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param array1 float浮点型一维数组 第一个有序数组
     * @param array2 float浮点型一维数组 第二个有序数组
     * @return float浮点型
     */
    public float find_median (float[] array1, float[] array2) {
        // 寻找两个数组中第k小的数
        // 每次从数组中早到k/2
        int n = array1.length;
        int m = array2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        float res1 = kmin(0, n - 1, array1, 0, m - 1, array2, left);
        float res2 = kmin(0, n - 1, array1, 0, m - 1, array2, right);

        if ((n + m) % 2 == 0) {
            return Math.min(res1, res2);
        }

        return (float) ((res1 + res2) * 0.5);

    }

    public static float kmin(int start1,int end1,float[] array1,int start2,int end2,float[] array2,int k){
        int len1 = end1-start1+1;
        int len2 = end2-start2+1;
        if(len1>len2){
            return kmin(start2,end2,array2,start1,end1,array1,k);
        }
        if(len1==0){
            return array2[start2+k-1];
        }
        if(k==1){
            return Math.min(array1[start1],array2[start2]);
        }
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;
        if (array1[i]>array2[j]){
            return kmin(start1, end1, array1, j + 1, end2, array2, k - j + start2 - 1);
        }else {
            return kmin(i + 1, end1, array1, start2, end2, array2, k - i + start1 - 1);
        }
    }
}


class p3{


    public static void main(String[] args) {

        p3 p3 = new p3();
        System.out.println(p3.bitwiseComplement(5));
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param n int整型
     * @return int整型
     */
    public int bitwiseComplement (int n) {
        // write code here

        // 转二进制
        // 转反
        // 转十进制
        String bin = Integer.toBinaryString(n);
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : bin.toCharArray()) {
            if (c == '0') {
                stringBuilder.append('1');
            }else {
                stringBuilder.append('0');
            }
        }

        return Integer.parseInt(stringBuilder.toString(), 2);

    }
}


class p4{

    public static void main(String[] args) {
        p4 p4 = new p4();
        int i = p4.giftExch(new int[]{2, 3, 7, 11, 13}, new int[]{1, 2, 3, 4, 5}, 30);
        System.out.println(i);

    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param points int整型一维数组 商品所需积分
     * @param counts int整型一维数组 商品数量
     * @param X int整型 用户拥有的积分
     * @return int整型
     */
    public int giftExch (int[] points, int[] counts, int X) {
        // write code here
        // 背包问题
        int[] dp = new int[X + 1];
        for (int i = 1; i <= X; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[0] = 0;


        for (int i = 0; i < points.length; i++) {
            int point = points[i];
            int count = counts[i];

            for (int j = X; j >= point; j--) {
                for (int k = 1; k <= count && j >= k * point; k++) {
                    dp[j] = Math.min(dp[j], dp[j - k * point] + k);
                }
            }
        }
        return dp[X] == Integer.MAX_VALUE ? -1 : dp[X];
    }



}

class DCBeiBao{


    public static void main(String[] args) {
        /**
         * 	重量	价值	数量
         * 物品0	1	15	2
         * 物品1	3	20	3
         * 物品2	4	30	2
         */
        int[] weights = {1, 3, 4};
        int[] values = {15, 20, 30};
        int[] sizes = {2, 3, 2};
        int pkgSize = 7;
        DCBeiBao dcBeiBao = new DCBeiBao();
        int i = dcBeiBao.maxProfit1(weights, values, sizes, pkgSize);
        System.out.println(i);
    }
    /**
     *
     * @param weights 重量
     * @param values 价值
     * @param sizes 物品数量
     */
    public int  maxProfit(int[] weights, int[] values, int[] sizes,int pkgSize) {

        // 先写一个01背包

        int[] dp = new int[pkgSize + 1];
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            int value = values[i];
            int size = sizes[i];
            for (int j = pkgSize; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + value);
                for (int k = 1; k <= size ; k++) {
                    // 遍历可以用的每个数量，每个数量都做一次判断，相当于k个物品
                    if (k * weight <= j) {
                        dp[j] = Math.max(dp[j], dp[j - k * weight] + k * value);
                    }else {
                        break;
                    }
                }
            }
        }

        return dp[pkgSize];
    }

    /**
     *
     * @param weights 重量
     * @param values 价值
     * @param sizes 物品数量
     */
    public int  maxProfit1(int[] weights, int[] values, int[] sizes,int pkgSize) {
        int sum = Arrays.stream(sizes).sum();
        int[] newWeights = new int[sum];
        int[] newValues = new int[sum];
        int index = 0;
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < sizes[i]; j++) {
                newWeights[index] = weights[i];
                newValues[index] = values[i];
                index++;
            }
        }

        weights = newWeights;
        values = newValues;


        // 先写一个01背包

        int[] dp = new int[pkgSize + 1];
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            int value = values[i];
            for (int j = pkgSize; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + value);
            }
        }

        return dp[pkgSize];
    }
}
