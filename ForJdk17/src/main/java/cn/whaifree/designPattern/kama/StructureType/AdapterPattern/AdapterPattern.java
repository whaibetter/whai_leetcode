package cn.whaifree.designPattern.kama.StructureType.AdapterPattern;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 18:58
 * @注释
 */
public class AdapterPattern {


    public static void main(String[] args) {
        USBAdapter usbAdapter = new USBAdapter();

        Computer computer = new Computer();



        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        for (int i1 = 0; i1 < i; i1++) {
            int i2 = scanner.nextInt();
            if (i2 == 1) {
                System.out.println(computer.inject(new TypeCInterface()));
            }else if (i2 == 2){
                USBInterface usbInterface = new USBInterface(); // USB接口
                TypeCInterface adapt = usbAdapter.adapt(usbInterface);// 适配器转换为TypeC
                System.out.println(computer.inject(adapt));
            }
        }
    }

}

class Computer{

    public String inject(TypeCInterface in) {
        return in.data;
    }

}

abstract class Interface{
    String data;
}

class USBAdapter {

    public TypeCInterface adapt(USBInterface usbInterface) {
        return new TypeCInterface(usbInterface.data + " Adapter");
    }
}
class USBInterface extends TypeCInterface {
    public USBInterface() {
        this.data = "USB";
    }
}
class TypeCInterface extends Interface {

    public TypeCInterface() {
        this.data = "TypeC";
    }
    public TypeCInterface(String data) {
        this.data = data;
    }
}
