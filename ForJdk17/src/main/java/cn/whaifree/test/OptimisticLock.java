package cn.whaifree.test;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/7 23:00
 * @注释
 */

public class OptimisticLock {

    @Data
    static class User{

        @Id
        @GeneratedValue
        int id;
        @Version
        private int version;
    }
}
