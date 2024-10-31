package cn.whaifree.springdemo.controller.redEnvelope;

import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 20:31
 * @注释
 */
@RestController
@RequestMapping("/redEnvelope")
public class RedEnvelopeController  {



    @Resource
    private RedissonClient redissonClient;


    @GetMapping(value = "/redisson/{key}")
    public String redissonTest(@PathVariable("key") String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock();
            Thread.sleep(10000);
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
        return "已解锁";
    }

    @PostMapping("distribute")
    public String rob(Double amount) {
        // 构造红包数据
        Map<String, Object> redPacket = new HashMap<>();
        redPacket.put("amount", amount);
        redPacket.put("id", System.currentTimeMillis()); // 使用当前时间戳作为红包 ID

        // 存入 Redis 红包列表
        RBucket<Object> redPacketList = redissonClient.getBucket("red_packet_list");
        redPacketList.set(redPacket);

        return "Distributed successfully";
    }


    @PostMapping("rob")
    public String rob(String userId) {

        return "";
    }

}
