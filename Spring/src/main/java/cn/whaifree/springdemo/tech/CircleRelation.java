//package cn.whaifree.springdemo.tech;
//
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// * @version 1.0
// * @Author whai文海
// * @Date 2024/10/31 13:57
// * @注释
// */
//public class CircleRelation {
//
//
//}
//
//
//// ================================================================
//
//@Component
////@Scope("prototype")
//class A1{
////    @Resource
////    public B1 b1;
//
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cn.whaifree.springdemo.tech");
////        A1 bean = context.getBean(A1.class);
////        System.out.println(bean.b1.a1);
//
////        LazyA1 lazyA1 = context.getBean(LazyA1.class);
////        System.out.println(lazyA1.b1);
//    }
//}
//
///**
// * BeanCurrentlyInCreationException:
// * Error creating bean with name 'a1':
// * Requested bean is currently in creation: Is there an unresolvable circular reference?
// *
// * A 实例创建后，populateBean 时，会触发 B 的加载。
// * B 实例创建后，populateBean 时，会触发 A 的加载。由于 A 的 scope=prototype，需要的时候都会创建一个全新的 A。
// * 这样，就会进入一个死循环。Spring 肯定是解决不了这种情况下的循环依赖的。所以，提前进行了 check，并抛出了异常。
// *
// * A需要一个新的B，B需要一个新的A，不能用为初始化的A活着B
// */
//@Component
////@Scope("prototype")
//class B1{
//    @Resource
//    public A1 a1;
//}
//
//
//@Component
//@Scope("prototype")
//class LazyA1{
//
//    @Autowired
//    @Lazy
//    public LazyB1 b1;
//}
///**
// *
// */
//@Component
//@Scope("prototype")
//class LazyB1{
//    @Autowired
//    public LazyA1 a1;
//}
//// ================================================================
//
//
//@Component
//class ConstructA {
//    ConstructB b;
//    public ConstructA(@Lazy ConstructB b) {
//        this.b = b;
//    }
//}
//@Component
//class ConstructB {
//    ConstructA a;
//    public ConstructB(ConstructA a) {
//        this.a = a;
//    }
//    public static void main(String[] args) {
//        /**
//         * A 实例在创建时(createBeanInstance)，由于是构造注入，这时会触发 B 的加载。
//         * B 实例在创建时(createBeanInstance)，又会触发 A 的加载，此时，A 还没有添加到三级缓存（工厂）中，所以就会创建一个全新的 A。
//         * 这样，就会进入一个死循环。Spring 是解决不了这种情况下的循环依赖的。所以，提前进行了 check，并抛出了异常。
//         *
//         * 解决：@Lazy放在构造器上，这样，A 实例在创建时，不会触发 B 的加载。
//         *
//         * 构造函数注入要求在创建bean实例时必须提供所有依赖
//         *
//         */
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cn.whaifree.springdemo.tech");
//        ConstructA bean = context.getBean(ConstructA.class);
//        System.out.println(bean.b);
//        // Unsatisfied dependency expressed through constructor parameter 0:
//        // Error creating bean with name 'constructA': Requested bean is currently in creation: Is there an unresolvable circular reference?
//    }
//}
//
//
//
//@Component
//class SetA {
//    SetB b;
//
//    @Autowired
//    public void setB(SetB b) {
//        this.b = b;
//    }
//}
//@Component
//class SetB {
//
//    SetA a;
//
//    @Autowired
//    public void setA(SetA a) {
//        this.a = a;
//    }
//
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("cn.whaifree.springdemo.tech");
//        SetA bean = context.getBean(SetA.class);
//        System.out.println(bean.b);
//    }
//}
