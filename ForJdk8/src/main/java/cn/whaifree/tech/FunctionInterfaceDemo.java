package cn.whaifree.tech;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 19:18
 * @注释
 */
public class FunctionInterfaceDemo {
    public static void main(String[] args) {
        filter(List.of("null", "this", "null"));
    }

    public static String filter(List<String> strings) {
        for (String string : strings) {
            if (string.equals("null")) {
                strings.remove(string);
            }
        }
        System.out.println(strings);
        return null;
    }

    static ReentrantLock lock = new ReentrantLock();


//    public static void main(String[] args) throws InterruptedException {
//
//        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
//        map.put(null, null);
////        HashMap<Object, Object> map = new HashMap<>();
////        map.put(null, null);
//        System.out.println(map.get(null));
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("1");
//                try {
//                    lock.lock();
//                    throw new RuntimeException();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock();
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("2");
//                try {
//                    lock.lock();
//                    throw new RuntimeException();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock();
//                }
//            }
//        }).start();
//
//
////        MyInterface myInterface = x -> x;
////        int run = myInterface.run(1);
////        System.out.println(run);
//    }

}

class MyClass {
    public static void doSome(MyInterface myInterface) {
    }

}

@FunctionalInterface
interface MyInterface {

    abstract int run(int x);

}
