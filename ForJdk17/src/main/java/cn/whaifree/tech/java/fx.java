package cn.whaifree.tech.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 22:24
 * @注释
 */
public class fx {

    class Base{

    }

    class User <T extends Base, M,N,O, P> { // ,是并列的关系


    }

    interface interfaceM{

    }

    class Book <T extends Base & interfaceM> { // 必须类在前，接口在后

    }

    class BookBase extends Base implements interfaceM{
        // 满足，同时是Base的子类和interfaceM的实现类
    }

    public void method() {
        new Book<BookBase>();
    }
}
