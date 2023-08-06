package com.imooc.pan.server.common.annotation;

import java.lang.annotation.*;

/**
 * 该注解之主要影响需要分享码校验的接口
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NeedShareCode {
}
