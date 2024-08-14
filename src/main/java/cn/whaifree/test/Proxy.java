package cn.whaifree.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Proxy {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Rent.class);
        enhancer.setCallback(new Agency());
        Rent o = (Rent) enhancer.create();
        System.out.println("起始的钱：" + o.money);
        o.rent();
        System.out.println("剩下的钱：" + o.money);
    }

    static class Rent{
        int money = 100;

        void rent() {
            System.out.println("租房！");
        }
    }

    static class Agency implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Rent o1 = (Rent) o;
            o1.money -= 20;
            System.out.println("扣费20为中介费");
            return methodProxy.invokeSuper(o, objects);
            //https://blog.csdn.net/z69183787/article/details/106878203
            /**
             * 调用invoke实际是调用cglib生成的子类的方法
             * 这个子类
             * method{
             *       interceptor(obj.method)
             * }
             * 正确使用：
             * 将obj从其他地方传进来
             *
             *
             * 调用invokeSuper实际是调用父类的方法method
             */
        }

    }

}
