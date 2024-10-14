package cn.whaifree.springdemo.RedisData;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/14 22:06
 * @注释
 */
@SpringBootTest
@Slf4j
public class RedisDataTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testHyperloglog() {
        String day1 = "visituser:article1:20211014";
        redisTemplate.opsForHyperLogLog().add(day1, "user1");
        redisTemplate.opsForHyperLogLog().add(day1, "user2");
        redisTemplate.opsForHyperLogLog().add(day1, "user3");
        redisTemplate.opsForHyperLogLog().add(day1, "user4");
        redisTemplate.opsForHyperLogLog().add(day1, "user5");
        // 获取值
        long count = redisTemplate.opsForHyperLogLog().size(day1);
        log.info("count day1:{}", count);//5 第一天有5个人访问这个文章

        String day2 = "visituser:article1:20211015";
        redisTemplate.opsForHyperLogLog().add(day2, "user1");
        redisTemplate.opsForHyperLogLog().add(day2, "user2");
        redisTemplate.opsForHyperLogLog().add(day2, "user3");
        redisTemplate.opsForHyperLogLog().add(day2, "user4");
        redisTemplate.opsForHyperLogLog().add(day2, "user6");

        long count2 = redisTemplate.opsForHyperLogLog().size(day2);
        log.info("count day2:{}", count2); //5


        Long union = redisTemplate.opsForHyperLogLog().union(day1, day2);
        log.info("union:{}", union);// 这个文章两天内的，有6个人访问
    }

    @Test
    public void testBitMap() {

        // 定义一个字符串变量key，用于存储Redis中存储位图的键
        String key = "bitmap:article1";

        // 在Redis位图中，将key对应的值的第1个位设置为true，这里用于记录文章1的阅读状态
        redisTemplate.opsForValue().setBit(key, 1, true);
        // 同样在Redis位图中，将key对应的值的第2个位设置为true，这里用于记录文章2的阅读状态
        redisTemplate.opsForValue().setBit(key, 2, true);

        // 检查Redis位图中key对应的值的第1个位是否为true，打印结果
        System.out.println(redisTemplate.opsForValue().getBit(key, 1));






        // 创建BitFieldSubCommands对象，用于执行更复杂的位图操作
        BitFieldSubCommands subCommands = BitFieldSubCommands.create();
        // 添加一个指令到subCommands中，获取第11个位（从0开始计数）的64位整型值
        subCommands.get(BitFieldSubCommands.BitFieldType.INT_64).valueAt(11);
        // 添加一个指令到subCommands中，将第11个位设置为64位整型值1
        subCommands.set(BitFieldSubCommands.BitFieldType.INT_64).to(11);
        // 执行subCommands中所有位图操作指令
        redisTemplate.opsForValue().bitField(key, subCommands);
        // 获取Redis位图中key对应的值的第11个位，检查其是否为true
        System.out.println(redisTemplate.opsForValue().getBit(key, 11));

    }

}
