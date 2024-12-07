package com.whai.springcloud.mybatis.mybatisplus.MybatisTest;


import com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 12:43
 * @注释
 */
@SpringBootTest
public class MybatisTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add(i);
                    list.remove(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(list.get(i));
                }
            }
        }).start();
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {




        StringBuilder sql = new StringBuilder();
    }


//    @Resource
//    private OrdersMapper ordersMapper;
//    @Test
//    public void test() {
//
//        List<Orders> orders = ordersMapper.selectList(null);
//        System.out.println(orders);
////        List<Orders> orders1 = ordersMapper.selectByExample();
////        System.out.println(orders1);
//    }
}
