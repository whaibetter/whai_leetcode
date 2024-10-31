package cn.whaifree.springdemo.utils.BroomFilter;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/30 21:01
 * @注释
 */
@Component
public class BroomFilter{
    RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);

    private int hashCount; // 哈希函数数量
    private int size;    // 位图大小
    private String key;  // Redis 中的 Bitmap 键

    public BroomFilter() {
        this.hashCount = 10;
        this.size = 1000000;
        this.key = "broomFilter";
    }

    public boolean contains(Object o) {
        // 计算Hashcode
        int[] hash = getHash(o);
        for (int i : hash) {
            // 检查位图中对应位置是否为true
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }

    public void addToBroom(Object o) {

        // 计算Hashcode
        int[] hash = getHash(o);
        for (int i : hash) {
            // 设置位图中对应位置为true
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    // 生成哈希值
    private int[] getHash(Object value) {
        int[] result = new int[hashCount];
        for (int i = 0; i < hashCount; i++) {
            result[i] = Math.abs(value.hashCode() + i * 123456) % size;
        }
        return result;
    }
}
