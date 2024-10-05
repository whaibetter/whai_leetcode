package cn.whaifree.designPattern.kama.StructureType.FacadePattern;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 18:33
 * @注释
 */
public class SpringFactoryBean {
}

//class ProxyFactoryBean extends ProxyCreatorSupport implements FactoryBean<Object>, BeanClassLoaderAware, BeanFactoryAware {
//
//
//    /**
//     * 门面模式的获取对象
//     * @return
//     * @throws BeansException
//     */
//    @Nullable
//    public Object getObject() throws BeansException {
//        this.initializeAdvisorChain();
//        if (this.isSingleton()) {
//            return this.getSingletonInstance();
//        } else {
//            if (this.targetName == null) {
//                this.logger.info("Using non-singleton proxies with singleton targets is often undesirable. Enable prototype proxies by setting the 'targetName' property.");
//            }
//
//            return this.newPrototypeInstance();
//        }
//    }
//
//    private synchronized Object getSingletonInstance() {
//        if (this.singletonInstance == null) {
//            this.targetSource = this.freshTargetSource();
//            if (this.autodetectInterfaces && this.getProxiedInterfaces().length == 0 && !this.isProxyTargetClass()) {
//                Class<?> targetClass = this.getTargetClass();
//                if (targetClass == null) {
//                    throw new FactoryBeanNotInitializedException("Cannot determine target class for proxy");
//                }
//
//                this.setInterfaces(ClassUtils.getAllInterfacesForClass(targetClass, this.proxyClassLoader));
//            }
//
//            super.setFrozen(this.freezeProxy);
//            this.singletonInstance = this.getProxy(this.createAopProxy());
//        }
//
//        return this.singletonInstance;
//    }
//
//}

