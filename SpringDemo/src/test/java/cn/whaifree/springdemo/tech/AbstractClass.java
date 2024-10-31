package cn.whaifree.springdemo.tech;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/20 20:19
 * @注释
 */
public class AbstractClass {

    void method() {

    }

    abstract class parents{

        public parents(int n) {
            System.out.println(inter.name);
            System.out.println(1);
        }

        public void method2() {

        }

        abstract void method();

    }

    class SubClass extends parents {
        public SubClass() {
            // System.out.println(1); 不能写在前面
            super(1);
        }

        @Override
        public void method() {
            method2();
        }
    }
}
interface inter{
    public static final int name = 0; // public static final 是多余的
}
