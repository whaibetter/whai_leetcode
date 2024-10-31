package cn.whaifree.designPattern.Factory;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 21:14
 * @注释
 */
public class FactoryAndStrategy {

    public static void main(String[] args) {
        Factory factory = new Factory();
        Pay pay = factory.getPay(PayType.WX);
        pay.pay(100);
        Pay pay2 = factory.getPay(PayType.WX);
        pay2.pay(200);

    }

    interface Pay{
        void pay(int price);
    }

    static class WxPay implements Pay {
        @Override
        public void pay(int price) {
            System.out.println("微信支付:" + price);
        }
    }
    static class AliPay implements Pay {

        @Override
        public void pay(int price) {
            System.out.println("支付宝支付:" + price);
        }
    }


    static class Factory{
        public Pay getPay(PayType payType){
            return payType.pay;
        }
    }

    enum PayType {
        WX(new WxPay()),
        ALI(new AliPay()), // 单例
        ;

        private Pay pay;

        PayType(Pay pay) {
            this.pay = pay;
        }
    }

}


