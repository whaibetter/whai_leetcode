package cn.whaifree.designPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

public class StrategyPattern {

    enum PayType{
        ALI(1, "阿里", new AliPayStrategy()),
        WX(2,"微信", new WxPayStrategy());

        int code;
        String name;
        PayStrategy payStrategy;

        PayType(int code, String name, PayStrategy payStrategy) {
            this.code = code;
            this.name = name;
            this.payStrategy = payStrategy;
        }

        public static PayStrategy getPayStrategy(Integer code) {
            for (PayType value : values()) {
                if (value.code == code) {
                    return value.payStrategy;
                }
            }
            return null;
        }
    }

    interface PayStrategy {

        boolean pay();
    }

    static class AliPayStrategy implements PayStrategy {

        @Override
        public boolean pay() {
            System.out.println("ali");
            return false;
        }
    }

    static class WxPayStrategy implements PayStrategy {

        @Override
        public boolean pay() {
            System.out.println("wx");
            return false;
        }
    }


    static class PayService {
        public void pay(Integer code) {
            PayStrategy payStrategy = PayType.getPayStrategy(code);
            if (payStrategy != null) {
                payStrategy.pay();
            }
        }
    }


    public static void main(String[] args) {
        new PayService().pay(2);
    }
}


/**
 * 在 springboot 中使用策略模式
 *
 * 1. 使用 Map<String,OprImpl>将接口转为 Map<Opr,OprImpl>
 * 2. 根据 Opr 在 Context 中操作
 *
 * 优点：新增操作只要加 类、操作（开闭，扩展开、修改闭），而不用修改原始代码 ifelse 这样
 *      每个操作独立，不影响其他操作
 *
 */
@Component
class Opr{
    @Autowired
    private OperationContext context;


    enum OprEnum{
        FAVOR(1),
        COMMENT(2),
        ;
        int oprNum = 0;

        OprEnum(int oprNum) {
            this.oprNum = oprNum;
        }
    }

    interface BlogOperation{
        void operation(String data);
    }

    @Component
    static class FavorOperation implements  BlogOperation{
        @Override
        public void operation(String data) {
            // 操作数据库
            System.out.println("点赞" + data);
        }
    }

    @Component
    static class CommentOperation implements  BlogOperation{
        @Override
        public void operation(String data) {
            // 操作数据库
            System.out.println("评论" + data);
        }
    }

    @Component
    static class OperationContext{
        private ConcurrentHashMap<OprEnum, BlogOperation> map = null;

        /**
         *
         * @param blogOperationConcurrentHashMap
         */
        @Autowired
        public OperationContext(ConcurrentHashMap<String, BlogOperation> blogOperationConcurrentHashMap) {
            map = new ConcurrentHashMap<>();
            map.put(OprEnum.FAVOR, blogOperationConcurrentHashMap.get("favorOperation"));
            map.put(OprEnum.COMMENT, blogOperationConcurrentHashMap.get("commentOperation"));
        }

        public void opr(BlogOperation blogOperation, String data) {
            BlogOperation opr = map.get(blogOperation);
            opr.operation(data);
        }

    }


}

enum OperationEnum{

    FAVOUR(1, "点赞", new Operation() {
        @Override
        public Object action(OperationEnum operationEnum) {

            return null;
        }
    }),
    COMMENT(2,"评论", new Operation() {
        @Override
        public Object action(OperationEnum operationEnum) {
            return null;
        }
    });


    int code;
    String oprName;
    Operation oprExecution;


    OperationEnum(int code, String oprName, Operation operation) {
        this.code = code;
        this.oprName = oprName;
        this.oprExecution = operation;
    }


}

interface Operation{
    Object action(OperationEnum operationEnum);
}



class HiShareStrategyPattern{



    public static void main(String[] args) {

    }
}



