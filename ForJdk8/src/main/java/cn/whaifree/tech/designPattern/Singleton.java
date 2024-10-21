package cn.whaifree.tech.designPattern;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/21 12:33
 * @注释
 */
public class Singleton {


    // 饿
    static class HungrySingleton {
        private static final Singleton INSTANCE = new Singleton();

        public static Singleton getInstance() {
            return INSTANCE;
        }
    }


    class StaticSingleton{
        volatile StaticSingleton staticSingleton = null;
        public StaticSingleton getInstance() {
            if (staticSingleton == null) {
                // 两个线程有一个卡在这里、有一个进去获得StaticSingleton.class
                synchronized (StaticSingleton.class) {
                    if (staticSingleton == null) { // 如果没有这个，那么另一个线程进来后也会new，覆盖了
                        staticSingleton = new StaticSingleton();
                    }
                }
            }
            return staticSingleton;
        }
    }

    // 懒汉式（线程安全，同步方法）
    static class SynchronizedSingleton{
        private static SynchronizedSingleton synchronizedSingleton = null;
        public static synchronized SynchronizedSingleton getInstance() {
            if (synchronizedSingleton == null) {
                synchronizedSingleton = new SynchronizedSingleton();
            }
            return synchronizedSingleton;
        }
    }


    // 静态内部累，静态类第一次调用才会初始化
    static class staticInnerClass{
        private static class SingletonHolder{
            private static final Singleton INSTANCE = new Singleton();
        }
        public static Singleton getInstance(){
            return SingletonHolder.INSTANCE; // 第一次调用SingletonHolder才会生成 INSTANCE
        }
    }

    // 枚举
}
