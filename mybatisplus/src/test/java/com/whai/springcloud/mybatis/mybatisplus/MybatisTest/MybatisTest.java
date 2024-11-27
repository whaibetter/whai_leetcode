package com.whai.springcloud.mybatis.mybatisplus.MybatisTest;




import com.whai.springcloud.mybatis.mybatisplus.mybatis.domain.User;
import com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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



    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        User user = new User();
        user.setId(1L);
        userMapper.getlist1(1l).forEach(System.out::println);
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
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
