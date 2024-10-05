package cn.whaifree.designPattern.kama.StructureType.FacadePattern;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 18:15
 * @注释
 */
public class FacadePattern {
    // 提供了一个统一的接口，用于访问子系统中的一群接口

    public class Facade {
        private Switch ac;
        private Switch lamp;
        private Switch tv;

        public Facade(Switch ac, Switch lamp, Switch tv) {
            this.ac = ac;
            this.lamp = lamp;
            this.tv = tv;
        }

        public void turnOffAll() {
            ac.off();
            lamp.off();
            tv.off();
        }

        public void turnOff(int o) {
            switch (o) {
                case 1:
                    ac.off();
                    break;
                case 2:
                    lamp.off();
                    break;
                case 3:
                    tv.off();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

    }

    // SubSystemClasses
// 开关
    interface Switch{
        void off();
    }

    //空调
    class AirConditioning implements Switch {

        @Override
        public void off() {
            System.out.println("Air Conditioner is turned off.");
        }
    }

    //台灯
    class DeskLamp implements Switch {

        @Override
        public void off() {
            System.out.println("Desk Lamp is turned off.");
        }
    }

    //电视机
    class Television implements Switch {
        @Override
        public void off() {
            System.out.println("Television is turned off.");
        }
    }

}
