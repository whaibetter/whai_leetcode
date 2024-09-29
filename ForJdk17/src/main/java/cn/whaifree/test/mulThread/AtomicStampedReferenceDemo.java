package cn.whaifree.test.mulThread;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/21 21:08
 * @注释
 */
public class AtomicStampedReferenceDemo {

    static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {

        User user = new User("张三", 18);
        AtomicStampedReference<User> atomicStampedReference = new AtomicStampedReference<>(user, 1);
        for (int i = 0; i < 10; i++) {
            User newUser = new User("李四", 20);
            boolean result = update(atomicStampedReference, newUser);
            System.out.println(result);
        }

    }


    public static boolean update(AtomicStampedReference<User> atomicStampedReference, User newUser) {
        User oldUser = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();

        while (!atomicStampedReference.compareAndSet(oldUser, newUser, stamp, stamp + 1)) {
            // 获取失败，更新User和stamp
            oldUser = atomicStampedReference.getReference();
            stamp = atomicStampedReference.getStamp();
        }
        return true;
    }
}
