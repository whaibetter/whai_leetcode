package cn.whaifree.tech.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/3 19:10
 * @注释
 */
public class FunctionInterfaceDemo {

    public static void main(String[] args) {
        MyInterface myInterface = x -> x;
        int run = myInterface.run(1);
        System.out.println(run);
    }

}

class MyClass {
    public static void doSome(MyInterface myInterface) {
    }

}


interface MyInterface {

    abstract int run(int x);
}
