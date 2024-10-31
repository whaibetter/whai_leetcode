package cn.whaifree.springdemo.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/9 17:47
 * @注释
 */
@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = {"unchecked", "rawtypes"})
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer jsonRedisSerializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jsonRedisSerializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jsonRedisSerializer);

        template.afterPropertiesSet();
        return template;
    }


    /**
     * RedisScript是Redis官方Java客户端Jedis中的一个类，用于执行Redis脚本（Lua脚本）。
     *
     * 在Redis中，Lua脚本是一种强大的工具，可以用于执行复杂的操作，如事务、发布/订阅、锁等。通过使用Lua脚本，你可以将多个操作封装在一个脚本中，然后一次性发送给Redis服务器执行，这样可以减少网络延迟和服务器负载。
     *
     * RedisScript类提供了一些方法，可以用于设置脚本的返回类型、执行脚本、获取脚本的返回值等。
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Long> limitScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(limitScriptText());
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    /**
     * 限流脚本
     */
    private String limitScriptText() {

        // 传入的参数有 time多长时间 count多少次

        return  "local key = KEYS[1]\n" + //test:cn.whaifree.springdemo.controller.TestController-test
                "local count = tonumber(ARGV[1])\n" + // 传入的次数， 比如1次
                "local time = tonumber(ARGV[2])\n" + // 传入的时间，比如2s
                "local current = redis.call('get', key);\n" + // 获取当前的次数
                "if current and tonumber(current) > count then\n" +
                "    return tonumber(current);\n" + // 当前的次数超过count 表示当前的次数超过限额，直接返回，表示拒绝
                "end\n" +
                "current = redis.call('incr', key)\n" + // 如果没有超过限额，对value，即current增加
                "if tonumber(current) == 1 then\n" + // 如果是第一次，增加过期时间
                "    redis.call('expire', key, time)\n" +
                "end\n" +
                "return tonumber(current);";
    }
}
