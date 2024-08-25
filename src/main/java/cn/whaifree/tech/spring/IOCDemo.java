package cn.whaifree.tech.spring;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 1. 容器，能够扫描包获取Bean
 *      能够使用反射创建对象
 *      能够DI，反射获取参数并从容器中获取
 *
 * 2. 注解
 *
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/8/25 11:16
 * @注释
 */
public class IOCDemo {


}



// 定义容器接口
interface Container {
    <T> T getBean(Class<T> clazz);
}

// 容器实现类
class SimpleIoCContainer implements Container {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    @Override
    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }

    public <T> void registerBean(Class<T> clazz, T instance) {
        beans.put(clazz, instance);
    }

    
}

// 示例类
class Service {
    public String hello() {
        return "Hello, World!";
    }
}

class Main {
    public static void main(String[] args) {
        // 创建容器实例
        SimpleIoCContainer container = new SimpleIoCContainer();

        // 注册服务
        Service service = new Service();
        container.registerBean(Service.class, service);

        // 从容器中获取服务
        Service retrievedService = container.getBean(Service.class);

        // 使用服务
        System.out.println(retrievedService.hello());
    }
}
