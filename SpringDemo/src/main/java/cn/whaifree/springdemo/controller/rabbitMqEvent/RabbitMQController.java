package cn.whaifree.springdemo.controller.rabbitMqEvent;

import cn.hutool.core.date.DateUtil;
import cn.whaifree.springdemo.utils.ResVo;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 19:20
 * @注释
 */
@RestController
@Slf4j
public class RabbitMQController {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/send")
    public String send(NotifyTypeEnum type, String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConstants.EXCHANGE, RabbitMQConstants.ROUTER, new NotifyMsgEvent<>(null, type, msg));
        return "success";
    }

    @RabbitListener(queues = RabbitMQConstants.QUEUE)
    void synBlogConsumer(Message msg , Channel channel) throws IOException {
        try {
            log.info("synBlogConsumer 接收到消息：{}", msg.toString());
            consumer(msg, "user1"); // 某个用户

            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("synBlogConsumer 接收消息失败：{}", e.getMessage());
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    private final String oprKey = "oprLog";

    /**
     * 消息重复消费？幂等性
     * @param message
     * @return
     */
    public void consumer(Message message,String user) {
        // 转为NotifyMsgEvent
        NotifyMsgEvent<String> event = JSON.parseObject(message.getBody(), NotifyMsgEvent.class);

        // 构造活跃度增加的通知
    }


    final String todayRankKey = DateUtil.format(DateUtil.date(), "yyyyMMdd");
    @PostMapping("/activity")
    public ResVo getRank(int k) {
        // 获取前k个
        Set<String> execute = redisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> collect = connection.zRange(todayRankKey.getBytes(), 0, k - 1).stream().map(new Function<byte[], String>() {
                @Override
                public String apply(byte[] bytes) {
                    return new String(bytes);
                }
            }).collect(Collectors.toSet());
            return collect;
        });
        return ResVo.success(execute);
    }

    public void incrDecrByActivity(ActivityScoreBo activityScore,String userId) {

        if (userId == null) {
            return;
        }

        String field;
        int score = 0;
        if (activityScore.getPath() != null) { // 关于页面
            field = "path_" + activityScore.getPath();
            score = 1;
        } else if (activityScore.getArticleId() != null) { // 关于文章
            field = activityScore.getArticleId() + "_";
            if (activityScore.getPraise() != null) {
                field += "praise";
                score = BooleanUtils.isTrue(activityScore.getPraise()) ? 2 : -2;
            } else if (activityScore.getCollect() != null) {
                field += "collect";
                score = BooleanUtils.isTrue(activityScore.getCollect()) ? 2 : -2;
            } else if (activityScore.getRate() != null) {
                // 评论回复
                field += "rate";
                score = BooleanUtils.isTrue(activityScore.getRate()) ? 3 : -3;
            } else if (BooleanUtils.isTrue(activityScore.getPublishArticle())) {
                // 发布文章
                field += "publish";
                score += 10;
            }
        } else if (activityScore.getFollowedUserId() != null) { // 关于关注
            field = activityScore.getFollowedUserId() + "_follow";
            score = BooleanUtils.isTrue(activityScore.getFollow()) ? 2 : -2;
        } else {
            return;
        }


        // 幂等性
        final String userActionKey = "ActivityCore:" + userId + DateUtil.format(DateUtil.date(), ":yyyyMMdd");

        // {user:{action1,action2}}
        Integer opr = (Integer) redisTemplate.opsForHash().get(userActionKey, field);
        if (opr == null) { // 某个用户在之前是否做过field这个操作
            // 没有操作过
            // 加记录
            redisTemplate.opsForHash().put(userActionKey, field, score);
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.expire(userActionKey.getBytes(), 31 * 24 * 60 * 60); // 保存一个月
                return null;
            });

            // 加分
            // 更新当天和当月的活跃度排行榜
            final String todayRankKey = DateUtil.format(DateUtil.date(), "yyyyMMdd");
            final String monthRankKey = DateUtil.format(DateUtil.date(), "yyyyMM");


            Double newAns = redisTemplate.execute(new RedisCallback<Double>() {
                @Override
                public Double doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.zScore(todayRankKey.getBytes(), userId.getBytes());
                }
            });
            Object execute = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.zScore(monthRankKey.getBytes(), userId.getBytes());
                }
            });

            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.expire(todayRankKey.getBytes(), 31 * 24 * 60 * 60); // 保存一个月
                return null;
            });
            redisTemplate.execute((RedisCallback<Object>) connection -> {
                connection.expire(monthRankKey.getBytes(), 31 * 24 * 60 * 60 * 12); // 保存一年
                return null;
            });
        } else if (opr > 0 && score < 0) {
            // 减分
            Long delete = redisTemplate.opsForHash().delete(userActionKey, field);
            if (delete == 1) {
                // 减分成功
                // 更新日、月排行榜
                final String todayRankKey = DateUtil.format(DateUtil.date(), "yyyyMMdd");
                final String monthRankKey = DateUtil.format(DateUtil.date(), "yyyyMM");

                redisTemplate.opsForHash().increment(todayRankKey, userId, -score);
                redisTemplate.opsForHash().increment(monthRankKey, userId, -score);
            }

        }

    }

    @Data
    @Accessors(chain = true)
    public class ActivityScoreBo {
        /**
         * 访问页面增加活跃度
         */
        private String path;

        /**
         * 目标文章
         */
        private Long articleId;

        /**
         * 评论增加活跃度
         */
        private Boolean rate;

        /**
         * 点赞增加活跃度
         */
        private Boolean praise;

        /**
         * 收藏增加活跃度
         */
        private Boolean collect;

        /**
         * 发布文章增加活跃度
         */
        private Boolean publishArticle;

        /**
         * 被关注的用户
         */
        private Long followedUserId;

        /**
         * 关注增加活跃度
         */
        private Boolean follow;
    }


}



@Configuration
class RabbitMQConfig {
    @Bean
    Queue aqueue() {
        return QueueBuilder.durable(RabbitMQConstants.QUEUE)
                .ttl(1000).maxLength(5)
                .deadLetterExchange(RabbitMQConstants.EXCHANGE).deadLetterRoutingKey(RabbitMQConstants.FAIL_ROUTER)
                .build();
    }
    @Bean
    Queue failQueue() {
        return QueueBuilder.durable(RabbitMQConstants.FAIL_QUEUE)
                .build();
    }

    @Bean
    Exchange commentExchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstants.EXCHANGE).durable(true).build();
    }

    @Bean
    Binding commentBinding() {
        return BindingBuilder.bind(aqueue()).to(commentExchange()).with(RabbitMQConstants.ROUTER).noargs();
    }
}

class RabbitMQConstants{
    public static final String QUEUE = "queue";
    public static final String FAIL_QUEUE = "fail_queue";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTER = "router";
    public static final String FAIL_ROUTER = "fail_router";
}
