package cn.whaifree.tech.SPI;

import java.security.Provider;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/15 21:31
 * @注释
 */
public interface SPIService {
    void sayHello();


    static void main(String[] args) {


        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        for (SPIService spiService : load) {
            System.out.println(spiService);
        }
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService next = iterator.next();
            next.sayHello();
        }
    }
}

