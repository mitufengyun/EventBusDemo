package com.example.library;

import java.lang.reflect.Method;

/**
 * time: 2020/3/14 10:34
 * author: xpf
 * describe: 保存符合要求订阅方法的封装类
 */
public class MethodManager {

    //参数类型
    private Class<?> type;
    //线程模式
    private ThreadMode threadMode;
    //执行订阅方法
    private Method method;

    public MethodManager(Class<?> type, ThreadMode threadMode, Method method) {
        this.type = type;
        this.threadMode = threadMode;
        this.method = method;
    }


    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ThreadMode getThreadMode() {
        return threadMode;
    }

    public void setThreadMode(ThreadMode threadMode) {
        this.threadMode = threadMode;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "MethodManager{" +
                "type=" + type +
                ", threadMode=" + threadMode +
                ", method=" + method +
                '}';
    }


}
