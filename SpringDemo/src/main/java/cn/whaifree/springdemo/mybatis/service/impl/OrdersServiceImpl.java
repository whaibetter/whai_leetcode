package cn.whaifree.springdemo.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.whaifree.springdemo.mybatis.domain.Orders;
import cn.whaifree.springdemo.mybatis.service.OrdersService;
import cn.whaifree.springdemo.mybatis.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
* @author wenhai
* @description 针对表【orders】的数据库操作Service实现
* @createDate 2024-11-01 12:45:47
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

}




