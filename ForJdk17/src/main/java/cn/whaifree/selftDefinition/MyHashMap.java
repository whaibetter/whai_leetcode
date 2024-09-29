package cn.whaifree.selftDefinition;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/29 21:11
 * @注释
 */
public class MyHashMap<K, V> {


    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();
        myHashMap.put("12", "12");
        for (int i = 0; i < 100; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }
        System.out.println(myHashMap.size());
        for (int i = 0; i < 100; i++) {
            System.out.println(myHashMap.get(String.valueOf(i)));
        }

    }

    class Node<K,V>{

        private K key;
        private V value;
        private Node<K,V> next;
        public Node(K key,V value){
            this.key = key;
            this.value = value;
        }
        public Node(){
        }

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    final float LOAD_FACTOR = 0.75f;
    private int size;
    Node<K, V>[] buckets;
    final int DEFAULT_CAPACITY = 16;

    public MyHashMap(int size) {
        buckets = new Node[size];
        size = 0;
    }
    public MyHashMap() {
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(K key,int length) {
        return key.hashCode() % length;
    }

    public void put(K key, V value)  {
        put(key, value, buckets);
    }


    public void put(K key, V value,Node<K, V>[] myBuckets)  {
        if (size >= myBuckets.length * LOAD_FACTOR) {
            resize();
        }

        // 根据hash获取桶的位置
        int loc = getIndex(key, myBuckets.length);
        Node<K, V> node = myBuckets[loc];
        if (node == null) {
            // 1. 空
            myBuckets[loc] = new Node<>(key, value);
        }else {
            // 2. 不空，尾插入法
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(key, value);
        }
        size++;
    }

    public V get(K key) {
        int loc = getIndex(key,buckets.length);
        if (buckets[loc] == null) {
            return null;
        }else {
            Node<K, V> node = buckets[loc];
            while (node != null) {
                if (node.key.equals(key)) {
                    return node.value;
                }
                node = node.next;
            }
        }
        return null;
    }

    public void resize() {
        // 扩容
        size = 0;
        Node<K, V>[] newBuckets = new Node[buckets.length * 2];
        reHash(newBuckets);
        buckets = newBuckets;
    }

    /**
     * 把原来元素全部重新散列到新表
     * @param newBuckets
     */
    public void reHash(Node<K, V>[] newBuckets) {
        for (Node<K, V> node : buckets) {
            if (node == null) {
                continue;
            }
            while (node != null) {
                put(node.key, node.value, newBuckets);
                node = node.next;
            }
        }
    }

    public int size() {
        return size;
    }
}
