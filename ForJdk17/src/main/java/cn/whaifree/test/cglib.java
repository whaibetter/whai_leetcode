package cn.whaifree.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/1 22:15
 * @注释
 */
public class cglib {

    public static void main(String[] args) {
        // 创建目标类的代理
        TargetClassProxy proxy = new TargetClassProxy();

        // 使用CGLIB创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetClass.class);
        enhancer.setCallback(proxy);

        TargetClass targetProxy = (TargetClass) enhancer.create(); // Enhancer创建子类代理

        // 调用代理对象的方法
        targetProxy.doSomething();
        System.out.println("===================");
        targetProxy.doA();
    }
}

class TargetClass extends TargetA{
    public void doSomething() {
        System.out.println("TargetClass: doSomething()");
    }
}

class TargetA{
    /**
     *  加了final后，Enhancer不会增强
     * <h4>
     * Before method: doSomething <br>
     * TargetClass: doSomething()<br>
     * After method: doSomething<br>
     * ===================<br>
     * TargetA: doA()<br>
     * </h4>
     *
     * 不加final
     * <h4>
     *     Before method: doSomething<br>
     * TargetClass: doSomething()<br>
     * After method: doSomething<br>
     * ===================<br>
     * Before method: doA<br>
     * TargetA: doA()<br>
     * After method: doA<br>
     * </h4>
     */
    public void doA(){

        System.out.println("TargetA: doA()");
    }
}

class TargetClassProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method: " + method.getName());
        Object result = proxy.invokeSuper(obj, args); // 调用目标类的原始方法
        System.out.println("After method: " + method.getName());
        return result;
    }
}

