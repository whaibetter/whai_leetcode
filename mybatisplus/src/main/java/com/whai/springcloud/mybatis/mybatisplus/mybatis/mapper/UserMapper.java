package com.whai.springcloud.mybatis.mybatisplus.mybatis.mapper;


import cn.whai.middleware.db.router.annotation.DBRouter;
import cn.whai.middleware.db.router.annotation.DBRouterStrategy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whai.springcloud.mybatis.mybatisplus.mybatis.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/25 11:22
 * @注释
 */

@Mapper
@DBRouterStrategy(splitTable = true)
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    @DBRouter(key = "id")
    List<User> getlist(User user);

    @Select("select * from user")
    @DBRouter(key = "id")
    List<User> getlist1(long l);
}
