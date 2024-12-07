package cn.whaifree.tech.java;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/6 15:02
 * @注释
 */
public class basicData {
    public static void main(String[] args) {

        System.out.println(0.1 + 0.2);
        System.out.println(0.2 - 0.1);
        System.out.println(0.1 * 0.2);
        System.out.println(0.2 / 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.3 / 0.1);


        double number = -1.0;
        long bits = Double.doubleToRawLongBits(number);
        System.out.println("Long Bits: " + bits);
        String binaryString = Long.toBinaryString(bits);
        System.out.println("Double value: " + number);
        System.out.println("Binary representation: " + binaryString);

        /**
         * 符号位、指数位和尾数位（也叫有效数字部分）
         *
         * 符号位 | 指数位 | 尾数位
         *
         */

        float number1 = -5.75F;
        long bits1 = Float.floatToIntBits(number1);
        System.out.println("Long Bits: " + bits1);
        String binaryString1 = Long.toBinaryString(bits1);
        System.out.println("Float value: " + number1);
        System.out.println("Binary representation: " + binaryString1);


    }
}
