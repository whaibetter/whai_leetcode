package cn.whaifree.test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/14 14:58
 * @注释
 */
public class LambdaClass {
    public static void main(String[] args) {
        new LambdaClass().lambdaInterfaceDemo(()-> System.out.println("自定义函数式接口"));
    }
    //函数式接口参数
    void lambdaInterfaceDemo(Custom i){
        i.f();
    }
}
