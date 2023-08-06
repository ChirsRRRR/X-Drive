package com.imooc.pan.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * 项目公用返回结果类
 */
// 保证序列化json的时候,如果是null的对象,key也会消失
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态描述
     */
    private String message;

    /**
     * 返回承载数据
     */
    private T data;

    private R(Integer code) {
        this.code = code;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return this.code.equals(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success() {
            return new R<>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success(String message) {
            return new R<>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> R<T> success(T data) {
            return new R<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> R<T> data(T data) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> R<T> fail() {
            return new R<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    public static <T> R<T> fail(String message) {
            return new R<>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> R<T> fail(Integer code, String message) {
            return new R<>(code, message);
    }

    public static <T> R<T> fail(ResponseCode responseCode) {
            return new R<>(responseCode.getCode(), responseCode.getDesc());
    }

}
