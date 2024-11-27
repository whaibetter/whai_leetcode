package cn.whaifree.springdemo.controller.idempotence;

import cn.whaifree.springdemo.utils.ResVo;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 幂等性测试
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/11 16:06
 * @注释
 */
@RestController
@RequestMapping("/idempotence")
public class IdempotenceController {

    @Resource
    JdbcTemplate jdbcTemplate;
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("query")
    public ResVo query() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from orders");
        return ResVo.success(JSON.toJSONString(maps));
    }

    /**
     * CREATE TABLE `orders` (
     * `id` INT AUTO_INCREMENT PRIMARY KEY,
     * `order_no` VARCHAR(100) UNIQUE NOT NULL COMMENT '订单号', -- 唯一
     * `user_id` INT NOT NULL COMMENT '用户ID',
     * `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
     * `status` TINYINT DEFAULT 0 COMMENT '订单状态: 0-未支付, 1-已支付',
     * `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
     * );
     * <p>
     * java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '1' for key 'order_no'
     * at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:118) ~[mysql-connector-j-8.0.33.jar:8.0.33]
     * at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122) ~[mysql-connector-j-8.0.33.jar:8.0.33]
     *
     * @param orderId
     * @param userId
     * @param totalAmount
     * @return
     */
    @PostMapping("insertByMySQL")
    public ResVo insertMySQL(String orderId, String userId, Integer totalAmount) {

        int update = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "INSERT INTO orders (order_no, user_id, total_amount) " +
                            "VALUES (?, ?, ?);"
            );
            preparedStatement.setString(1, orderId);
            preparedStatement.setString(2, userId);
            preparedStatement.setString(3, String.valueOf(totalAmount));
            return preparedStatement;
        });

        return update > 0 ? ResVo.success("插入成功") : ResVo.error("插入失败");
    }

    /**
     * 适用于更新操作
     *
     * @return
     */
    @PostMapping("upadteCAS")
    public ResVo updateCAS(String orderId, Integer totalAmount, Integer originVersion) {

        int update = jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "UPDATE orders " +
                            "SET total_amount = ?, version=version+1 " +
                            "WHERE order_no = ? AND version = ?");
            preparedStatement.setInt(1, totalAmount);
            preparedStatement.setString(2, orderId);
            preparedStatement.setInt(3, originVersion);
            return preparedStatement;
        });


        return update > 0 ? ResVo.success("更新成功") : ResVo.error("更新失败");
    }


    final String tokenV = "tokenV";

    /**
     * Token防刷
     */
    @PostMapping("token")
    public ResVo generateToken() {
        // 生成UUID
        String s = UUID.randomUUID().toString();
        String key = "token:" + s;
        redisTemplate.opsForValue().set(key, tokenV, 60, TimeUnit.SECONDS);
        return ResVo.success(key);
    }

    @PostMapping("tokenCheck")
    public ResVo tokenCheck(String key) {
        String script =
                // 有这个token则通过，并且删除，只能一次性使用
                "if redis.call('get',KEYS[1]) == ARGV[1] " + // key argv为参数
                "then " +
                        "return redis.call('del',KEYS[1]) " +
                "else " +
                        "return 0 end";

        DefaultRedisScript<Long> tDefaultRedisScript = new DefaultRedisScript<>(script, Long.class);

        Long execute = redisTemplate.execute(tDefaultRedisScript, Arrays.asList(key), tokenV);
        if (execute == 0) {
            throw new RuntimeException("未通过");
        }
        return ResVo.success("通过");
    }
}
