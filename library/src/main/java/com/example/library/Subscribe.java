package com.example.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * time: 2020/3/14 10:28
 * author: xpf
 * describe: 订阅事件注解
 */
@Target(ElementType.METHOD)//作用在方法之上
@Retention(RetentionPolicy.RUNTIME)//jvm在运行时通过反射获取注解的值
public @interface Subscribe {

    ThreadMode threadMode() default ThreadMode.POSTING;

}
