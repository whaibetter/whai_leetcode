package cn.whaifree.tech.SPI;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/15 21:31
 * @注释
 */
public class ASPIServiceImpl implements SPIService {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am ASPIServiceImpl");
    }
}
