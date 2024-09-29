package cn.whaifree.interview.Meituan;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/30 19:09
 * @注释
 */
public class MT3_39 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        // xz + x = tl
        // yd - y = xz
        // xz + yd + tl = k
        int tl = (k - 2 * x - y) / 3;
        int xz = tl + x;
        int yd = xz + y;
        System.out.print(tl + " " + yd + " " + xz);

    }

    static class p2{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int[] nums = new int[n];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
                max = Math.max(max, nums[i]);
            }


            for (int num : nums) {
                if (num * 2 > max) {
                    System.out.print(num * 2);
                    System.out.print(" ");
                } else {
                    System.out.print(max);
                    System.out.print(" ");
                }
            }
        }
    }

    static class p4{
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String s1 = scanner.next();
            String s2 = scanner.next();
            char[] c1 = s1.toCharArray();
            char[] c2 = s2.toCharArray();
            // 遇到不一样的就对前面全部不一样的操作
            int index = s1.length()-1;


            int flag = 0;
            for (int i = 0; i < c1.length; i++) {
                if (c1[i] == c1[i + 1] && c2[i] != c2[i + 1]) {
                    flag = 0;
                    break;
                } else if (c1[i] != c1[i + 1] && c2[i] == c2[i + 1]) {
                    flag = 1;
                    break;
                } else if (c1[i] != c1[i + 1] && c2[i] != c2[i + 1]) {
                    flag = 2;
                    break;
                }
            }

            if (flag == 0) {
                List<String> list = new ArrayList<>();
                while (index >= 0) {
                    if (c1[index]!= c2[index]) {
                        for (int i = index; i >= 0; i--) {
                            c2[i] = c1[index];
                        }
                        list.add("2 " + (index + 1) + " " + c1[index]);
                    }
                    index--;
                }
                System.out.println(list.size());
                list.forEach(System.out::println);
            } else  {
                List<String> list2 = new ArrayList<>();
                index = s1.length() - 1;
                c1 = s1.toCharArray();
                c2 = s2.toCharArray();
                while (index >= 0) {
                    if (c2[index]!= c1[index]) {
                        for (int i = index; i >= 0; i--) {
                            c1[i] = c2[index];
                        }
                        list2.add("1 " + (index + 1) + " " + c2[index]);
                    }
                    index--;
                }
                System.out.println(list2.size());
                list2.forEach(System.out::println);
            }



        }

    }

    static class p5{
        static long res = 0;
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            String s = scanner.next();
            char[] chars = s.toCharArray();

            HashMap<Character, Integer> map = new HashMap<>();
            backTacking(chars, 0, map);
            System.out.println(res);
        }

        public static void backTacking(char[] chars, int start, Map<Character,Integer> map) {
            if (map.size() == 2 && isEqualValue(map)) {
                res++;
            }
            if (start > chars.length || map.size() > 2) {
                return;
            }

            for (int i = start; i < chars.length; i++) {
                map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
                backTacking(chars, i + 1, map);
                Integer integer = map.get(chars[i]);
                if (integer == 1) {
                    map.remove(chars[i]);
                }else {
                    map.put(chars[i], map.get(chars[i]) - 1);
                }
            }
        }

        public static boolean isEqualValue(Map<Character, Integer> map) {
            // 判断map的所有value是否一样
            for (Integer value : map.values()) {
                if (value != map.values().iterator().next()) {
                    return false;
                }
            }
            return true;
        }
    }

//    static class p6{
//        static int res = 0;
//        public static void main(String[] args) {
//
//            Scanner scanner = new Scanner(System.in);
//            int n = scanner.nextInt();
//            String s = scanner.next();
//            char[] chars = s.toCharArray();
//
//            ArrayList<Integer> map = new ArrayList<>();
//            backTacking(chars, 0, map);
//            System.out.println(res);
//        }
//
//        public static void backTacking(char[] chars, int start, ArrayList<Integer> map) {
//            if (map.size() == 2 && isEqualValue(map)) {
//                res++;
//            }
//            if (start > chars.length || map.size() > 2) {
//                return;
//            }
//
//            for (int i = start; i < chars.length; i++) {
//                map;
//                backTacking(chars, i + 1, map);
//                Integer integer = map.get(chars[i]);
//                if (integer == 1) {
//                    map.remove(chars[i]);
//                }else {
//                    map.put(chars[i], map.get(chars[i]) - 1);
//                }
//            }
//        }
//
//        public static boolean isEqualValue(Map<Character, Integer> map) {
//            // 判断map的所有value是否一样
//            for (Integer value : map.values()) {
//                if (value != map.values().iterator().next()) {
//                    return false;
//                }
//            }
//            return true;
//        }
//    }

    static class p7{

        /**
         * 小美有n个朋友，她准备请其中一些朋友来吃饭。
         * 其中有一些朋友有暗恋关系：假设a暗恋b，那么小美带上b的时候a也必须去，否则a就会不开心。
         * 小美想知道，一共有多少种不同的请客方案可以让每个人都开心？由于答案可能过大，请对10^9+7取模。
         * @param args
         */
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int peopleNumber = scanner.nextInt();
            int relation = scanner.nextInt();
            int[] rela = new int[peopleNumber + 1];
            for (int i = 0; i < relation; i++) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                rela[from] = to;
            }
            // rela 表示 index的人暗恋 里面值的那个
            // 1 2
            // 2 3
            // 3 4




        }
    }
}
