package cn.whaifree.springdemo.controller.rabbitMqEvent;

import cn.hutool.extra.spring.SpringUtil;
import cn.whaifree.springdemo.utils.ResVo;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/15 19:12
 * @注释
 */
@RestController
public class EventController {

    /**
     * 根据不同的青桔
     *
     * @param msg
     * @return
     */
    @PostMapping("/sendMsg")
    public ResVo sendMsgNotify(NotifyTypeEnum notifyType, String msg) {
        // 发送异步消息
        SpringUtil.publishEvent(new NotifyMsgEvent<>(this, notifyType, msg));
        return ResVo.success();
    }
}

@Getter
@ToString
class NotifyMsgEvent<T> extends ApplicationEvent {
    private NotifyTypeEnum notifyType;
    private T content;
    public NotifyMsgEvent(Object source, NotifyTypeEnum notifyType, T content) {
        super(source);
        this.notifyType = notifyType;
        this.content = content;
    }
}


@Service
class NotifyMsgListener<T> implements ApplicationListener<NotifyMsgEvent<T>> {
    @Override
    public void onApplicationEvent(NotifyMsgEvent<T> event) {
        System.out.println(event); // 获取到发送的消息,做下一步处理
    }
}



@Getter
enum NotifyTypeEnum {
    COMMENT(1, "评论"),
    REPLY(2, "回复"),
    PRAISE(3, "点赞"),
    COLLECT(4, "收藏"),
    FOLLOW(5, "关注消息"),
    SYSTEM(6, "系统消息"),
    DELETE_COMMENT(1, "删除评论"),
    DELETE_REPLY(2, "删除回复"),
    CANCEL_PRAISE(3, "取消点赞"),
    CANCEL_COLLECT(4, "取消收藏"),
    CANCEL_FOLLOW(5, "取消关注"),

    // 注册、登录添加系统相关提示消息
    REGISTER(6, "用户注册"),
    LOGIN(6, "用户登录"),
    ;


    private int type;
    private String msg;

    private static Map<Integer, NotifyTypeEnum> mapper;

    static {
        mapper = new HashMap<>();
        for (NotifyTypeEnum type : values()) {
            mapper.put(type.type, type);
        }
    }

    NotifyTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static NotifyTypeEnum typeOf(int type) {
        return mapper.get(type);
    }

    public static NotifyTypeEnum typeOf(String type) {
        return valueOf(type.toUpperCase().trim());
    }
}
