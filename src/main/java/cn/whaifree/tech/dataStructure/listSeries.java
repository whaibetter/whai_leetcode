package cn.whaifree.tech.dataStructure;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class listSeries {

    @Test
    public void test() throws IOException, ClassNotFoundException {

        ReentrantLock reentrantLock = new ReentrantLock();

    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<User> o = new ArrayList<>();
        o.add(new User(1, "110"));

        try(FileOutputStream fileOutputStream = new FileOutputStream("./user.ser")) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
        }


        // 恢复对象
        try(FileInputStream fileInputStream = new FileInputStream("./user.ser")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o1 = objectInputStream.readObject();
            List<User> users = (List<User>) o1;
            System.out.println(users);
        }

    }
    static class User implements Serializable{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
