package cn.whaifree.designPattern;

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
