package cn.whaifree.tech;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/15 21:22
 * @注释
 */
public class GRRXReference {

    @Test
    public void soft() {
        Object obj = new Object();
        SoftReference<Object> softRef = new SoftReference<>(obj); // 创建软引用

        // 手动触发垃圾回收
        System.gc();

        // 尝试获取软引用指向的对象
        Object retrievedObject = softRef.get();
        if (retrievedObject != null) {
            System.out.println("对象还在内存中： " + retrievedObject);
        } else {
            System.out.println("对象已经被垃圾回收");
        }

        // 使 obj 可被回收
        obj = null;

        // 再次尝试获取软引用指向的对象
        System.gc(); // 强烈建议调用 GC 来验证软引用回收
        retrievedObject = softRef.get();
        if (retrievedObject != null) {
            System.out.println("对象还在内存中： " + retrievedObject);
        } else {
            System.out.println("对象已经被垃圾回收");
        }
    }

    @Test
    public void weak() {
        Object obj = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(obj); // 创建弱引用

        // 手动触发垃圾回收
        System.gc();

        // 尝试获取软引用指向的对象
        Object retrievedObject = weakRef.get();
        if (retrievedObject != null) {
            System.out.println("对象还在内存中： " + retrievedObject);
        } else {
            System.out.println("对象已经被垃圾回收");
        }

        // 使 obj 可被回收
        obj = null;

        // 再次尝试获取软引用指向的对象
        System.gc(); // 强烈建议调用 GC 来验证软引用回收
        retrievedObject = weakRef.get();
        if (retrievedObject != null) {
            System.out.println("对象还在内存中： " + retrievedObject);
        } else {
            System.out.println("对象已经被垃圾回收");
        }
    }

    @Test
    public void phantom() {
        // 创建一个引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<>();

        // 创建一个对象和它的虚引用
        Object obj = new Object();
        PhantomReference<Object> phantomRef = new PhantomReference<>(obj, queue);

        System.out.println("虚引用创建完毕，obj 是：" + phantomRef.get());

        // 使 obj 可被回收
        obj = null;

        // 手动触发垃圾回收
        System.gc();

        // 检查引用队列中是否有虚引用指向的对象
        try {
            // 只有在对象被回收后，才会从引用队列中取得虚引用
            PhantomReference<?> refFromQueue = (PhantomReference<?>) queue.remove();
            System.out.println("虚引用指向的对象已经被回收：" + refFromQueue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
