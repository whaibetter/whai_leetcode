package cn.whaifree.interview.HKWS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/1 11:25
 * @注释
 */
public class P241001 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int left;
        left = Integer.parseInt(in.nextLine().trim());

        int right;
        right = Integer.parseInt(in.nextLine().trim());

        int[] res = new P241001().getPrimes(left, right);
        if(res==null||res.length==0){
            System.out.print("-1");
        }
        for(int res_i=0; res_i < res.length; res_i++) {
            System.out.print(String.valueOf(res[res_i])+" ");
        }

    }

    /* Write Code Here */
    public int[] getPrimes(int left, int right) {

        List<Integer> res = new ArrayList<>();
        while (left <= right) {
            if (isPrime(left)) {
                res.add(left);
            }
            left++;
        }
        int[] re = new int[res.size()];
        for (int i = 0; i < re.length; i++) {
            re[i] = res.get(i);
        }
        return re;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if ( n % i == 0){
                return false;
            }
        }
        return true;
    }
}
