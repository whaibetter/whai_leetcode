//package cn.whaifree.interview.HKWS;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Iterator;
//
///**
// * @version 1.0
// * @Author whai文海
// * @Date 2024/9/4 21:03
// * @注释
// */
//public class Write {
//
//
//    public static void main(String[] args) {
//
//        ArrayList<Integer> list1 = new ArrayList<>();
//        ArrayList<Integer> list2 = new ArrayList<>();
//
//        for (int i = 0; i < 100000; i++) {
//            list2.add(i);
//        }
//
//        HashSet<Integer> set = new HashSet<>();
//        HashMap<Integer, Object> map = new HashMap<>();
//
//        for (int i = 30000; i < 50000; i++) {
//            list1.add(i);
//            set.add(i);
//            map.put(i, new Object());
//        }
//
//        Iterator<Integer> iterator1 = list1.iterator();
//        Iterator<Integer> iterator2 = list2.iterator();
//
//
//        long l = System.currentTimeMillis();
//        while (iterator2.hasNext()) {
//            if (list1.contains(iterator2.next())) { // 相当于顺序便利int[]
//                iterator2.remove();
//            }
//        }
//        System.out.println(System.currentTimeMillis() - l); // 2937
//
//
//        long l = System.currentTimeMillis();
//        list2.removeAll(list1);  // 底层也是顺序便利  c.contains 挨个查
//        System.out.println(System.currentTimeMillis() - l); // 2862
//
//
//        long l = System.currentTimeMillis();
//        list2.removeAll(set); // c.contains 【map.contain】很快
//        System.out.println(System.currentTimeMillis() - l); // 8
//
//
//        long l = System.currentTimeMillis();
//
//        while (iterator2.hasNext()) {
//            if (map.containsKey(iterator2.next())) { // map.containsKey
//                iterator2.remove();
//            }
//        }
//        System.out.println(System.currentTimeMillis() - l); // 107
//
//
//    }
//}
