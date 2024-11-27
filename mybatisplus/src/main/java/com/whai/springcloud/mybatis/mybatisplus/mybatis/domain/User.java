package com.whai.springcloud.mybatis.mybatisplus.mybatis.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/11/25 11:22
 * @注释
 */
@Data
@TableName("user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
