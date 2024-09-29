package cn.whaifree.redo.redo_all_240721;

import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 15:46
 * @注释
 */
public class LeetCode146 {

    @Test
    public void test()
    {
//        LRUCache cache = new LRUCache(2);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));
//        cache.put(3, 3);
//        System.out.println(cache.get(2));
//        cache.put(4, 4);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

        /**
         * ["LRUCache","put","put","put","put","get","get"]
         * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
         *
         */
        LRUCache cache1 = new LRUCache(2);
        cache1.put(2, 1);
        cache1.put(1, 1);
        cache1.put(2, 3);
        cache1.put(4, 1);
        System.out.println(cache1.get(1));
        System.out.println(cache1.get(2));
    }


    class LRUCache {

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(); // 有序
        int defaultSize = 0 ;
        public LRUCache(int capacity) {
            defaultSize = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Integer v = map.get(key);
                map.remove(key);
                map.put(key, v);
                return v;
            }
            return -1;
        }

        public void put(int key, int value) {

            if (map.containsKey(key)) {
                map.put(key, value);
                get(key);
                return;
            }

            map.put(key, value);
            if (map.size() > defaultSize) {
                for (Integer i : map.keySet()) {
                    map.remove(i);
                    break;
                }
            }
        }
    }

}
