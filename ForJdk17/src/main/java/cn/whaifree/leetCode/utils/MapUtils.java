package cn.whaifree.leetCode.utils;

import java.util.Map;

public class MapUtils {

    public  static void printMap(Map map) {
        if (map == null) {
            return;
        }
        for (Object key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
        System.out.println("==========");
    }
}
