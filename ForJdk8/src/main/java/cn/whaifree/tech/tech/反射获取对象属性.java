package cn.whaifree.tech.tech;

import java.lang.reflect.Field;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/6 15:23
 * @注释
 */
public  class 反射获取对象属性 {

    static class User{
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = User.class;
        for (Field field : aClass.getDeclaredFields()) { // declared包括所有字段
            field.setAccessible(true);
            String name = field.getName();
            System.out.println(name);
        }

    }
}
