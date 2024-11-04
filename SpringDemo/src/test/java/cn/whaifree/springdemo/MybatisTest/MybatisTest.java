package cn.whaifree.springdemo.MybatisTest;

import cn.whaifree.springdemo.mybatis.domain.Orders;
import cn.whaifree.springdemo.mybatis.mapper.OrdersMapper;
import cn.whaifree.springdemo.mybatis.service.OrdersService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/1 12:43
 * @注释
 */
@SpringBootTest
public class MybatisTest {

    @Resource
    private OrdersService ordersService;

    @Resource
    private OrdersMapper ordersMapper;
    @Test
    public void test() {

        List<Orders> orders = ordersMapper.selectList(null);
        System.out.println(orders);
        List<Orders> orders1 = ordersMapper.selectByExample();
        System.out.println(orders1);
    }
}
