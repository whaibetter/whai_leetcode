package cn.whaifree.leetCode;

public class StaticObjTest {
    static class Test{
        // 静态变量存方法区
        static ObjectHolder staticObj = new ObjectHolder();
        // 成员变量存堆中
        ObjectHolder instanceObj = new ObjectHolder();

        void foo(){
            // 变量名存放在foo()方法中的栈帧中的局部变量表中
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");
        }
    }


    private static class ObjectHolder{

    }
}
