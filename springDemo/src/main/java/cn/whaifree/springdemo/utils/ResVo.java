package cn.whaifree.springdemo.utils;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/11 16:30
 * @注释
 */


import cn.whaifree.springdemo.utils.constants.HttpStatus;

import java.util.HashMap;

/**
 * 操作消息提醒
 *
 * @author /
 */
public class ResVo extends HashMap<String, Object>
{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 ResVo 对象，使其表示一个空消息。
     */
    public ResVo()
    {
    }

    /**
     * 初始化一个新创建的 ResVo 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     */
    public ResVo(int code, String msg)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public boolean isEmpty(){
        return super.get(CODE_TAG).equals(HttpStatus.FOUND);
    }

    /**
     * 初始化一个新创建的 ResVo 对象
     *
     * @param code 状态码
     * @param msg 返回内容
     * @param data 数据对象
     */
    public ResVo(int code, String msg, Object data)
    {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResVo success()
    {
        return ResVo.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResVo success(Object data)
    {
        return ResVo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResVo success(String msg)
    {
        return ResVo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResVo success(String msg, Object data)
    {
        return new ResVo(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResVo warn(String msg)
    {
        return ResVo.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResVo warn(String msg, Object data)
    {
        return new ResVo(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static ResVo error()
    {
        return ResVo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static ResVo error(String msg)
    {
        return ResVo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static ResVo error(String msg, Object data)
    {
        return new ResVo(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static ResVo error(int code, String msg)
    {
        return new ResVo(code, msg, null);
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public ResVo put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }
}
