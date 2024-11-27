package com.whai.springcloud.mybatis.mybatisplus;

import com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MybatisplusApplicationTests {

    @Resource
    private UserMapper userMapper;


    @Test
    void contextLoads() {

//        userMapper.getlist(1).forEach(
//                System.out::println
//        );
//        List<User> list = userMapper.selectList(null);
//        list.forEach(System.out::println);

    }

}
