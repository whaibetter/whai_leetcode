package cn.whaifree.test;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/23 11:09
 * @注释
 */
public class MT {




//        public static void main(String[] args) {
//
//            Scanner in = new Scanner(System.in);
//
//            String[] s = in.nextLine().split(" ");
//
//            int a = Integer.parseInt(s[0]);
//            int b = Integer.parseInt(s[1]);
//            int[][] ints = new int[a][b];
//            for(int i = 0 ; i<a;i++){
//                String[] split = in.nextLine().split("");
//                int index = 0;
//                for(;index<b;index++){
//                    ints[i][index] = Integer.parseInt(split[index]);
//                }
//            }
//
//            int res = 0;
//            for (int i = 0; i < a - 1; i++) {
//                for (int j = 0; j < b - 1; j++) {
//                    if (compute(ints, i, j)) {
//                        res++;
//                    }
//                }
//            }
//            System.out.println(res);
//        }
//
//        public static boolean compute(int[][] ints ,int start,int startB){
//
//            int one = 0;
//            int zero = 0;
//            for(int i = start; i<start+2;i++){
//                for(int j = startB; j<startB+2;j++){
//                    if(ints[i][j]==1){
//                        one++;
//                    }else{
//                        zero++;
//                    }
//                }
//            }
//            return one == zero;
//
//        }


//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        String s = in.nextLine();
//
//        char[] chars = s.toCharArray();
//        int res = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = 0; j < s.length(); j++) {
//                if (isHuiWenAndOS(s, i, j)) {
//                    s = new String()
//                }
//            }
//        }
//
//
//    }
//
//    public static boolean isHuiWenAndOS(String s,int start,int end){
//
//        while (start < end) {
//            if (s.charAt(start) != s.charAt(end)) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//
//        return (end - start) % 2 == 0;
//    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        String[] split = in.nextLine().split(" ");

        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        String[] s = in.nextLine().split("");
        StringBuilder sb = new  StringBuilder();
        for(int i= 0;i<s.length;i+=4){
            int number = Integer.parseInt(s[i+2]);
            for(;number>0;number--){
                sb.append(s[i]);
            }
        }

        System.out.println(res(sb.toString().toCharArray(), k, 0));




    }

    public static int res(char[] chars,int k,int start){

        if(start>=chars.length){
            return 0;
        }

        int weight = 0;
        HashSet<Character> set = new HashSet<>();
        int i;
        for(i = start ; weight<k ;i++ ){
            set.add(chars[i]);
            weight = set.size() * (i - start + 1);
        }


        return weight < k ? 0 : res(chars, k, i) + 1;
    }


}
