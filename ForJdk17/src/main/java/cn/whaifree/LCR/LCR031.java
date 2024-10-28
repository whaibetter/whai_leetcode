package cn.whaifree.LCR;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/24 15:09
 * @注释
 */
public class LCR031 {


    class LRUCache {
        int maxCap = 0;
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        public LRUCache(int capacity) {
            maxCap = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Integer i = map.get(key);
                map.remove(key);
                map.put(key, i);
                return i;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                map.remove(key);
                map.get(key);
                map.put(key, value);
                return;
            }

            map.put(key, value);
            if (map.size() > maxCap) {
                for (Integer i : map.keySet()) {
                    map.remove(i); // 只删除一个
                    break;
                }
            }
        }
    }

    @Test
    public void test() {

        // ["LRUCache","put","put","get","put","put","get"]
        // [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }



/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
    @Test
    public void test2() {
        P2.LRUCache cache = new P2.LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }
}




 class P2{


    static class Entry{
        Entry before;
        Entry next;
        int value;
        int key;

        public Entry(Entry before, Entry next, int value, int key) {
            this.before = before;
            this.next = next;
            this.value = value;
            this.key = key;
        }
    }
    static class LRUCache {
        Map<Integer, Entry> map = new HashMap<>();
        Entry head;
        Entry tail;
        int maxSize;

        public LRUCache(int capacity) {
            maxSize = capacity;
            head = new Entry(null, null, Integer.MIN_VALUE, Integer.MIN_VALUE);
            tail = new Entry(null, null, Integer.MAX_VALUE, Integer.MAX_VALUE);
            tail.before = head;
            head.next = tail;
        }

        public void addToHead(Entry entry) {
            Entry next = head.next;
            head.next = entry;
            entry.before = head;
            entry.next = next;
            next.before = entry;
        }



        public void removeIndex(Entry entry) {
            entry.before.next = entry.next;
            entry.next.before = entry.before;
        }


        public int get(int key) {
            if (map.containsKey(key)) {
                Entry entry = map.get(key);
                removeIndex(entry);
                addToHead(entry);
                return entry.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Entry entry = map.get(key);
                entry.value = value;
                removeIndex(entry);
                addToHead(entry);
                return;
            }
            Entry newEntry = new Entry(null, null, value, key);
            addToHead(newEntry);
            map.put(key, newEntry);
            if (map.size() > maxSize) {
                Entry before = tail.before;
                map.remove(before.key);
                removeIndex(tail.before);
            }
        }
    }
}
