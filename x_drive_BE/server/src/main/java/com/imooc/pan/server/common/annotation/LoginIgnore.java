package com.imooc.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解之主要影响哪些不需要登录的接口
 * 标注该注解的方法会自动屏蔽统一的登录拦截校验的逻辑
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginIgnore {
}
