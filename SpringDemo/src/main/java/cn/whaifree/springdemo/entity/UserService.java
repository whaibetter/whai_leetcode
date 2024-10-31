package cn.whaifree.springdemo.entity;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 22:12
 * @注释
 */
@Configuration
@Order(-1)
class Config {

}

/**
 * <h1>Spring Bean生命周期</h1>
 * <p>Spring Bean 的生命周期包括创建、初始化、使用、销毁等过程。</p>
 * <p>Spring Bean 的生命周期</p>
 *
*      <li>实例化阶段：Bean 被实例化，Bean 实例化后，Bean 处于未初始化状态。</li>
*       <li>初始化阶段：Bean 完成实例化后，Bean 处于初始化状态，Bean 实例变量可以被赋值。</li>
*       <ul>
*           <li><code>BeanNameAware.setBeanName</code></li>
*           <li><code>BeanFactoryAware.setBeanFactory</code></li>
*           <li><code>ApplicationContextAware.setApplicationContext</code></li>
*       </ul>
*     <ul>
*         <li><code>@PostConstruct</code></li>
*         <li><code>InitializingBean.afterPropertiesSet</code></li>
*         <li><code>BeanPostProcessor.postProcessBeforeInitialization</code></li>
*         <li>初始化</li>
*         <li><code>BeanPostProcessor.postProcessAfterInitialization</code></li>
*     </ul>
*        <li>使用阶段：Bean 完成初始化后，Bean 处于可使用状态，Bean 可以被使用。</li>
*         <li>销毁阶段：Bean 不再被使用时，Bean 处于销毁状态，Bean 实例将被销毁。</li>
*
*          <ul>
*          <li><code>@PreDestroy</code></li>
*          <li><code>DisposableBean.destroy</code></li>
*
*
*
*      </ul>
*
 *
 *
 *
 *
 * <h2>相关Aware注入：让 Bean 能够**感知**到它们所运行的环境或依赖的资源e</h2>
 * <h2>为什么要叫Aware</h2>
 * Aware是一个接口，
 * 指示 Bean 有资格通过 Callback 样式方法由 Spring 容器通知特定框架对象。
 * 实际的方法签名由各个子接口确定，但通常应仅包含一个接受单个参数的返回 void 的方法
 */
//@Component
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, AutoCloseable {


    /**
     * 执行 BeanFactoryAware.setBeanFactory
     * 执行 ApplicationContextAware.setApplicationContext
     * 执行 @PostConstruct
     * UserService afterPropertiesSet
     * 执行 BeanPostProcessor.postProcessBeforeInitialization
     * 执行 BeanPostProcessor.postProcessAfterInitialization
     * 执行 @PreDestroy
     * UserService destroy
     *
     * @param args
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cn.whaifree.springdemo.entity");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        // 执行 DisposableBean

        context.close();
    }

    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 BeanPostProcessor.postProcessBeforeInitialization");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 BeanPostProcessor.postProcessAfterInitialization");
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserService afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserService destroy");
    }

    /**
     * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeAwareMethods(java.lang.String, java.lang.Object)
     *
     * if (bean instanceof BeanFactoryAware beanFactoryAware) { // 如果实现了BeanFactoryAware接口，就执行setBeanFactory
     * 	beanFactoryAware.setBeanFactory(AbstractAutowireCapableBeanFactory.this);
     *   }
     * @param beanFactory owning BeanFactory (never {@code null}).
     * The bean can immediately call methods on the factory.
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("执行 BeanFactoryAware.setBeanFactory");
    }




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("ApplicationContextAware.setApplicationContext(ApplicationContext applicationContext) 可以获取applicantionContext，便于获取context，可以提取变成一个公共的component，每次从这个component获取Bean");
    }


    @PostConstruct
    public void init() {
        System.out.println("执行 @PostConstruct");
    }

    @PreDestroy
    public void destroyMethod() {
        System.out.println("执行 @PreDestroy");
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("执行 BeanNameAware.setBeanName");
        // 修改原来的BeanName
    }


    /**
     * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeAwareMethods(java.lang.String, java.lang.Object)
     *
     * if (bean instanceof Aware) {
     * 			if (bean instanceof BeanNameAware beanNameAware) {
     * 				beanNameAware.setBeanName(beanName);
     *                        }
     * 			if (bean instanceof BeanClassLoaderAware beanClassLoaderAware) {
     * 				ClassLoader bcl = getBeanClassLoader();
     * 				if (bcl != null) {
     * 					beanClassLoaderAware.setBeanClassLoader(bcl);
     *                }
     *            }
     * 			if (bean instanceof BeanFactoryAware beanFactoryAware) {
     * 				beanFactoryAware.setBeanFactory(AbstractAutowireCapableBeanFactory.this);
     *            }        * 		}
     */


}

/**
 * 模拟工具类，其实就是可以随时获取本Context
 */
//@Component
//class SpringContextUtil implements ApplicationContextAware {
//    static ApplicationContext applicationContext;
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//    public static ApplicationContext getApplicationContext(){
//        return applicationContext;
//    }
//}
