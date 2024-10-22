package cn.whaifree.tech.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/22 23:13
 * @注释
 */
public class ProxyDemo {

    interface base{
        void print();
    }

    static class A implements base {

        @Override
        public void print() {
            System.out.println("A");
        }
    }


    public static void main(String[] args) {
        base a = (base)
                java.lang.reflect.Proxy.newProxyInstance(
                        base.class.getClassLoader(),
                        new Class[]{
                                base.class
                        },
                        new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                System.out.println("ProxyA");
                                method.invoke(new A(), args);
                                System.out.println("after invoke");
                                return null;
                            }
                        }
                );
        a.print();
    }
}
