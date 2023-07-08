package com.quanxiaoha.weblog.common.aspect;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ApiOperationLog {
    /**
     * 日志方法描述
     *
     * @return
     */
    String description() default "";

}

