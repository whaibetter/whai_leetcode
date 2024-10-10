package cn.whaifree.redo.redo_all_240924;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/8 11:22
 * @注释
 */
public class LeetCode146 {

    class LRUCache {

        class V{
            int value;
            V next;

            public V(int value, V next) {
                this.value = value;
                this.next = next;
            }
        }

        Map<Integer, V> map;
        int maxSize;
        Integer headIndex;


        public LRUCache(int capacity) {
            map = new HashMap<>();
            maxSize = capacity;
        }

        public int get(int key) {
            int value = map.get(key).value;
            map.remove(key);


            return value;
        }

        public void put(int key, int value) {
            V beforeHea = map.get(headIndex);
            V newV = new V(value, beforeHea);
            headIndex = key;
            map.put(key, newV);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
