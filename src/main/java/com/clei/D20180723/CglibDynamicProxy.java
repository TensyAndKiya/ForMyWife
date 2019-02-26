package com.clei.D20180723;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibDynamicProxy {
    public static void main(String[] args) {
        // Test
        UserDao userDao=new UserDaomMysqlImpl();
        UserDao userDaoProxy=(UserDao) new LogHandler2().getInstance(userDao);
        userDaoProxy.save(1,"张三");
        userDaoProxy.delete(1);
        System.out.println("目标对象类型： "+userDao.getClass());
        System.out.println("代理目标对象类型： "+userDaoProxy.getClass());
    }
}

class LogHandler2 implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        //创建增强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开启日志。。。");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("关闭日志。。。");
        return result;
    }
}
