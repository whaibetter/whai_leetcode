package cn.whaifree.redo.redo_all_241016;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/4 10:26
 * @注释
 */
public class LeetCode146 {

    @Test
    public void test() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        System.out.println(lruCache.get(1));
        lruCache.put(3,3);
        System.out.println(lruCache.get(2));
        lruCache.put(4,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    class LRUCache {
        static class ItemNode <K,V> {
            ItemNode<K,V> pre;
            ItemNode<K,V> next;
            K key;
            V value;

            public ItemNode(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public ItemNode() {
            }
        }

        Map<Integer, ItemNode<Integer,Integer>> map;
        int capacity;
        ItemNode<Integer, Integer> head;
        ItemNode<Integer, Integer> tail;


        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new ItemNode<>();
            tail = new ItemNode<>();
            head.next = tail;
            tail.pre = head;
        }

        // 添加到头部
        public void addToHead(ItemNode<Integer, Integer> itemNode) {
            ItemNode<Integer, Integer> tmp = head.next;
            head.next = itemNode;
            itemNode.pre = head;
            itemNode.next = tmp;
            tmp.pre = itemNode;
        }

        public void removeItem(ItemNode<Integer, Integer> itemNode) {
            itemNode.pre.next = itemNode.next;
            itemNode.next.pre = itemNode.pre;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            ItemNode<Integer, Integer> getThisNode = map.get(key);
            // 删除该节点
            removeItem(getThisNode);
            // 添加到头部
            addToHead(getThisNode);
            return getThisNode.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                ItemNode<Integer, Integer> origin = map.get(key);
                origin.value = value;
                get(key);
            } else {
                ItemNode<Integer, Integer> newItem = new ItemNode<>(key, value);
                map.put(key, newItem);
                addToHead(newItem);
                while (map.size() > capacity) {
                    ItemNode<Integer, Integer> del = tail.pre;
                    Integer delK = del.key;
                    map.remove(delK);
                    removeItem(del);
                }
            }
        }
    }

//    class LRUCache {
//        static class ItemNode{
//            ItemNode pre;
//            ItemNode next;
//            Integer key;
//            Integer value;
//
//            public ItemNode(Integer key, Integer value) {
//                this.key = key;
//                this.value = value;
//            }
//
//            public ItemNode() {
//            }
//        }
//
//        Map<Integer, ItemNode> map;
//        int capacity;
//        ItemNode head;
//        ItemNode tail;
//
//
//        public LRUCache(int capacity) {
//            map = new HashMap<>();
//            this.capacity = capacity;
//            head = new ItemNode();
//            tail = new ItemNode();
//            head.next = tail;
//            tail.pre = head;
//        }
//
//        // 添加到头部
//        public void addToHead(ItemNode itemNode) {
//            ItemNode tmp = head.next;
//            head.next = itemNode;
//            itemNode.pre = head;
//            itemNode.next = tmp;
//            tmp.pre = itemNode;
//        }
//
//        public void removeItem(ItemNode itemNode) {
//            itemNode.pre.next = itemNode.next;
//            itemNode.next.pre = itemNode.pre;
//        }
//
//        public int get(int key) {
//            if (!map.containsKey(key)) {
//                return -1;
//            }
//            ItemNode getThisNode = map.get(key);
//            // 删除该节点
//            removeItem(getThisNode);
//            // 添加到头部
//            addToHead(getThisNode);
//            return getThisNode.value;
//        }
//
//        public void put(int key, int value) {
//            if (map.containsKey(key)) {
//                ItemNode origin = map.get(key);
//                origin.value = value;
//                get(key);
//            } else {
//                ItemNode newItem = new ItemNode(key, value);
//                map.put(key, newItem);
//                addToHead(newItem);
//                while (map.size() > capacity) {
//                    ItemNode del = tail.pre;
//                    Integer delK = del.key;
//                    map.remove(delK);
//                    removeItem(del);
//                }
//            }
//        }
//    }


}
