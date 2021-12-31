package com.sly.hybrid.annotation;

import java.lang.annotation.*;

/**
 * 忽略token
 *
 * @author SLY
 * @date 2021/12/30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IgnoreToken {
}

