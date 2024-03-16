package cn.whaifree.leetCode;

import cn.whaifree.leetCode.model.TreeNode;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/6 19:14
 * @注释
 */
public class Tes1 {

//    public static void main(String[] args) {
////        new Tes1().localvarGc1();
////        new Tes1().localvarGc2();
////        new Tes1().localvarGc3();
//        new Tes1().localvarGc4();
//    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }

    /**
     * -XX:+PrintGCDetail
     */
    public void localvarGc1() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        //10MB
        System.gc(); // 成功回收PSYoungGen，移动到ParOldGen老年代
        /**
         * 新生代 PSYoungGen: 15482K->11192K(152576K 10mb
         * full gc后，移动到老年代 [Full GC (System.gc()) [PSYoungGen: 11192K->0K(152576K)] 0mb  [ParOldGen: 8K->10885K(348160K)]
         * [GC (System.gc()) [PSYoungGen: 15482K->11192K(152576K)] 15482K->11200K(500736K), 0.0059640 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         * [Full GC (System.gc()) [PSYoungGen: 11192K->0K(152576K)] [ParOldGen: 8K->10885K(348160K)] 11200K->10885K(500736K), [Metaspace: 3210K->3210K(1056768K)], 0.0060621 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         * Heap
         */
    }

    public void localvarGc2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;// 对象已经没被引用，byte[]被回收

        System.gc();
        /**
         * 回收 空余的堆new byte[10 * 1024 * 1024];
         * [GC (System.gc()) [PSYoungGen: 15482K->840K(152576K)] 15482K->848K(500736K), 0.0009380 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (System.gc()) [PSYoungGen: 840K->0K(152576K)] [ParOldGen: 8K->645K(348160K)] 848K->645K(500736K), [Metaspace: 3215K->3215K(1056768K)], 0.0043640 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         */
    }

    public void localvarGc3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
        /**
         * - 新生代 10mb
         * [GC (System.gc()) [PSYoungGen: 15482K->11112K(152576K)] 15482K->11120K(500736K), 0.0064283 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
         * - 进入老年代
         * [Full GC (System.gc()) [PSYoungGen: 11112K->0K(152576K)] [ParOldGen: 8K->10882K(348160K)] 11120K->10882K(500736K), [Metaspace: 3169K->3169K(1056768K)], 0.0041698 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]*/
    }

    public void localvarGc4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
        /**
         * // 局部变量表长度是2，第一个为this，第二个为value，替换了buffer，所以被回收了
         * [GC (System.gc()) [PSYoungGen: 15482K->904K(152576K)] 15482K->912K(500736K), 0.0006836 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         * [Full GC (System.gc()) [PSYoungGen: 904K->0K(152576K)] [ParOldGen: 8K->669K(348160K)] 912K->669K(500736K), [Metaspace: 3216K->3216K(1056768K)], 0.0043170 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
         */
    }

    public void localvarGc5() {
        localvarGc1();
        System.gc();
    }


    public static void main1(String[] args) {
        TreeNode treeNode = new TreeNode(11);
        SoftReference<TreeNode> treeNodeSoftReference = new SoftReference<>(treeNode);
        treeNode = null; // 取消强引用
        System.out.println(treeNodeSoftReference.get().val);

        System.gc();//回收

        System.out.println(treeNodeSoftReference.get().val); // 堆空间内存足够，不会回收软引用

        try {
            byte[] bytes = new byte[1024 * 1024 * 7]; //导致资源紧张、不够
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(treeNodeSoftReference.get()); // 在OOM之前，GC软引用对象treeNodeSoftReference
        }

    }

    private static final Object lock = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        // 创建一个TreeNode对象并创建其对应的PhantomReference和ReferenceQueue
        TreeNode treeNode = new TreeNode(11);
        ReferenceQueue<TreeNode> referenceQueue = new ReferenceQueue<>();
        PhantomReference<TreeNode> treeNodePhantomReference = new PhantomReference<>(treeNode, referenceQueue);

        // 取消对TreeNode对象的强引用
        treeNode = null;

        // 尝试输出虚引用的对象，由于虚引用不能直接获取对象，所以此行会报错或无输出
        // System.out.println(treeNodePhantomReference.get().val); // 不允许直接访问

        // 请求垃圾回收
        System.gc();

        // 虽然对象可能已经被回收，但由于虚引用特性，此处仍然不会被回收
        // 需要检查ReferenceQueue来判断对象是否已被垃圾回收器处理
        if (referenceQueue.poll() != null) {
            System.out.println("TreeNode对象已被垃圾回收器回收，但虚引用仍然存在");
        } else {
            System.out.println("TreeNode对象还未被垃圾回收器回收");
        }

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (count < 10) {
                    System.out.println("Thread 1: " + count++);
                    lock.notify();
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                while (count < 20) {
                    System.out.println("Thread 2: " + count++);
                    lock.notify();
                }
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        t1.start();
        t2.start();
    }



}

class Sun implements Inter,Outer {


    public static void main(String[] args) {
        new Sun().testDefault();
        Inter.testStatic();
    }

    /**
     * 必须重写
     */
    @Override
    public void testDefault() {
        Outer.super.testDefault();
        Inter.super.testDefault();
    }
}


interface Inter{

    default void testDefault(){
        System.out.println("testDefault");
    }

    static void testStatic(){
        System.out.println("testStatic");
    }
}

interface Outer{

    default void testDefault(){
        System.out.println("testDefault outer");
    }

    static void testStatic(){
        System.out.println("testStatic outer");
    }
}

