package cn.whaifree.springdemo.mybatis.mapper;

import cn.whaifree.springdemo.mybatis.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author wenhai
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2024-11-01 12:45:47
* @Entity generator.domain.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    @Select("SELECT * FROM orders")
    List<Orders> selectByExample();

}




