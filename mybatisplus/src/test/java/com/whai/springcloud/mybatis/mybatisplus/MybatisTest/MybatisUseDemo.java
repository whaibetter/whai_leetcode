package com.whai.springcloud.mybatis.mybatisplus.MybatisTest;

import com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/12/5 16:50
 * @注释
 */
@SpringBootTest
public class MybatisUseDemo {


    @Resource
    SqlSessionFactory sqlSessionFactory;


//    @Test
//    public void test() {
//        SqlSession sqlSession = sqlSessionFactory.openSession(); // 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//
//        List<User> users =
//                mapper.getlist1(1);
//        users.forEach(System.out::println);
//
//
//        System.out.println("====");
//
//        // 第二次查询使用缓存
//        users =
//                mapper.selectList(null);
//        users.forEach(System.out::println);
//
//        System.out.println("==第二次查询，使用了缓存==");
//
//
//        // 方法名称
//        sqlSession.select("com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper.selectList", new ResultHandler() {
//            @Override
//            public void handleResult(ResultContext resultContext) {
//                System.out.println(resultContext.getResultObject());
//            }
//        });
//
//        List<Object> objects = sqlSession.selectList(
//                "com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper.UserMapper.selectList",
//                null,
//                new RowBounds(2, 2)); // 这里是查询返回后再分页的
//        objects.forEach(System.out::println);
//
//
//
//
//        /**
//         * 本地缓存
//         *  每当一个新 session 被创建，MyBatis 就会创建一个与之相关联的本地缓存。
//         *  任何在 session 执行过的查询结果都会被保存在本地缓存中，
//         *  所以，当再次执行参数相同的相同查询时，就不需要实际查询数据库了。
//         *  本地缓存将会在做出修改、事务提交或回滚，以及关闭 session 时清空。
//         */
//
//
//
//        sqlSession.close();
//    }
//
//    @Test
//    public void test1() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        mapper.selectList(null).forEach(System.out::println);
//
//        SqlSession newSession = sqlSessionFactory.openSession();
//        newSession.getMapper(UserMapper.class).insert(new User());
//
//        mapper.selectList(null).forEach(System.out::println); // 直接用缓存数据，插入的数据没看着
//
//        // 只有在同一个session内的修改，才会让缓存失效
//
//    }

    @Test
    public void test2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getlist1(1).forEach(System.out::println);

//        SqlSession newSession = sqlSessionFactory.openSession();
//        newSession.getMapper(UserMapper.class).insert(new User());

        mapper.getlist1(1).forEach(System.out::println);


    }

}
