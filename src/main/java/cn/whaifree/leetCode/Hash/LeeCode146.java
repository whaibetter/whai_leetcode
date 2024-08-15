package cn.whaifree.leetCode.Hash;

import cn.whaifree.leetCode.model.ListNode;
import org.junit.Test;

import java.security.Key;
import java.util.HashMap;

public class LeeCode146 {

    @Test
    public void test()
    {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));


    }

    class LRUCache {

        static class EntryNode {
            Integer key = null;
            Integer object = null;
            EntryNode pre = null;
            EntryNode after = null;

            public EntryNode(Integer object, EntryNode pre, EntryNode after) {
                this.object = object;
                this.pre = pre;
                this.after = after;
            }

            public EntryNode(Integer key ,Integer object) {
                this.key = key;
                this.object = object;
            }

            public EntryNode() {
            }
        }

        HashMap<Integer,EntryNode> caches;
        EntryNode head;
        EntryNode tail;
        Integer size = null;


        public LRUCache(int capacity) {
            caches = new HashMap<>();
            size = capacity;
            head = new EntryNode();
            tail = new EntryNode();
            head.after = tail;
            tail.pre = head;
        }

        /**
         * 1. 先判断hashmap中有没有
         * 2. 如果有
         *
         * @param key
         * @return
         */
        public int get(int key) {
            EntryNode entryNode = caches.get(key);
            if (entryNode == null) {
                return -1;
            }
            deleteNode(entryNode);
            addNode(entryNode);
            return entryNode.object;
        }

        public void put(int key, int value) {

            if (caches.containsKey(key)) {
                EntryNode entryNode = caches.get(key);
                entryNode.object = value;
                deleteNode(entryNode);
                addNode(entryNode);
                return;
            }

            EntryNode v = new EntryNode(key,value);
            caches.put(key, v);
            addNode(v);
            if (caches.size() > size) {
                //删除最早那个
                caches.remove(head.after.key);
                deleteNode(head.after);
            }
        }


        /**
         * 删除某个节点
         * @param entryNode
         */
        public void deleteNode(EntryNode entryNode) {
            entryNode.pre.after = entryNode.after;
            entryNode.after.pre = entryNode.pre;
        }

        /**
         * 在尾部增加某个节点
         * @param node
         */
        public void addNode(EntryNode node) {
            EntryNode pre = tail.pre;
            pre.after = node;
            node.pre = pre;
            node.after = tail;
            tail.pre = node;
        }
    }

}
