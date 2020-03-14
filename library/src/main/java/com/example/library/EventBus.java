package com.example.library;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * time: 2020/3/14 10:06
 * author: xpf
 * describe:
 */
public class EventBus {

    private static volatile EventBus instance;

    //用来保存带注解方法(满足订阅条件的方法)
    private Map<Object, List<MethodManager>> cacheMap;

    private EventBus() {
        cacheMap = new HashMap<>();

    }

    public static EventBus getDefault() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    //收到信息方法
    //找到MainActivity所有符合注解的方法
    public void register(Object getter) {
        List<MethodManager> methodList = cacheMap.get(getter);
        if (methodList == null) {
            methodList = findAnnotationMethod(getter);
            //key代表MainActivity标识，value代表MainActivity中符合注解的方法的集合
            cacheMap.put(getter, methodList);
        }
    }

    /**
     * 通过注解找到对应的方法
     * @return
     */
    private List<MethodManager> findAnnotationMethod(Object getter) {
        List<MethodManager> methodList = new ArrayList<>();
        Class<?> clazz = getter.getClass();
//        Method[] methods = clazz.getDeclaredMethods();//获取当前类的所有方法
        Method[] methods = clazz.getMethods();//获取当前类和父类的所有方法
        //采用第一种方案就没有问题，采用第二种方案可以进一步优化
        while (clazz != null) {
            String clazzName = clazz.getName();
            if (clazzName.startsWith("java.") || clazzName.startsWith("javax.") || clazzName.startsWith("android.")) {
                break;
            }

            for (Method method : methods) {
                Subscribe subscribe = method.getAnnotation(Subscribe.class);
                if (subscribe == null) {
                    continue;
                }
                //校验方法是否满足条件
                //校验方法返回值类型
                Type returnType = method.getGenericReturnType();
                if (!"void".equals(returnType.toString())) {
                    throw new RuntimeException(method.getName() + "方法返回值类型必须是 void");
                }
                //校验方法参数
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new RuntimeException(method.getName() + "方法参数有且仅有一个");
                }
                //更多条件的校验
                //...
                //完全符合要求，添加到集合
                MethodManager manager = new MethodManager(parameterTypes[0], subscribe.threadMode(), method);
                methodList.add(manager);
            }
            //不断循环找出父类含有订阅方法，直至为空
            clazz = clazz.getSuperclass();
        }
        return methodList;
    }

    //发送操作，发布事件
    public void post(Object setter) {
        Set<Object> set = cacheMap.keySet();
        //获取MainActivity对象，即注册要发布消息的Activity或Fragment对象
        for (Object getter : set) {
            //获取MainActivity中所有带注解的方法
            List<MethodManager> managerList = cacheMap.get(getter);
            if (managerList != null) {
                //循环执行每个订阅方法
                for (MethodManager manager : managerList) {
                    //isAssignableFrom(用来匹配发布者和订阅者参数是否一致)
                    if (manager.getType().isAssignableFrom(setter.getClass())) {
                        invoke(manager, getter, setter);
                    }
                }
            }
        }
    }

    private void invoke(MethodManager manager, Object getter, Object setter) {
        Method method = manager.getMethod();
        try {
            method.invoke(getter, setter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
