package cn.whaifree.leetCode;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/27 17:58
 * @注释
 */
public class T {

    public int A() {
        B();
        return 1;
    }

    public void B() {

    }
}

interface Animal {
    void eat();
}

class Hunter{
   void hunt() {

   }
}

class Cat extends Hunter implements Animal {

    @Override
    void hunt() {
        System.out.println("cat hunt");
    }

    @Override
    public void eat() {
        System.out.println("cat eat");
    }
}

class Dog extends Hunter implements Animal {

    public Dog() {
        super();
    }

    public Dog(String name) {
        super();
    }

    @Override
    void hunt() {
        System.out.println("dog hunt");
    }

    @Override
    public void eat() {
        System.out.println("dog eat");
    }
}

class AnimalTest {
    public static void main(String[] args) {
        // 晚期绑定，动态链接
        // 运行时才能确定
        Animal animal = new Cat();
        animal.eat();
        Hunter hunter = (Hunter) animal;
        hunter.hunt();

        // 静态多态
        Dog dog = new Dog("!23");


    }
}


class Father{
    public static void print(String str){
        System. out. println("father "+str);
    }
    public void show(String str){
        System. out. println("father"+str);
    }
}

class Son extends Father {


}

class VirtualMethodTest {
    public static void main(String[] args) {
        Son.print("coder");
        Father fa = new Father();
        fa.show("a");
    }
}
