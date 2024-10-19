package cn.whaifree.springdemo.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 22:12
 * @注释
 */
@Configuration
@Order(-1)
class Config{

}
@Component("userService")
public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor,AutoCloseable {

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

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        System.out.println("执行 BeanFactoryAware.setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("执行 ApplicationContextAware.setApplicationContext");
    }

    @PostConstruct
    public void init(){
        System.out.println("执行 @PostConstruct");
    }

    @PreDestroy
    public void destroyMethod(){
        System.out.println("执行 @PreDestroy");
    }

    @Override
    public void close() throws Exception {

    }


    /**
     * org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeAwareMethods(java.lang.String, java.lang.Object)
     *
     * if (bean instanceof Aware) {
     * 			if (bean instanceof BeanNameAware beanNameAware) {
     * 				beanNameAware.setBeanName(beanName);
     * 			            }
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
