package com.clei.D20180723;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy {
    public static void main(String[] args){
        //ProxyFactory Test
        UserDao userDao=new UserDaomMysqlImpl();
        UserDao userDaoProxy=(UserDao) new ProxyFactory(userDao).getLogProxyInstance();
        userDaoProxy.save(1,"张三");
        userDaoProxy.delete(1);
        System.out.println("目标对象类型： "+userDao.getClass());
        System.out.println("代理目标对象类型： "+userDaoProxy.getClass());
        //LogHandler Test
        UserDao userDao2=new UserDaomOracleImpl();
        UserDao userDaoProxy2=(UserDao) new LogHandler(userDao2).getInstance();
        userDaoProxy2.save(2,"李四");
        userDaoProxy2.delete(2);
        System.out.println("目标对象类型： "+userDao2.getClass());
        System.out.println("代理目标对象类型： "+userDaoProxy2.getClass());
    }
}

interface UserDao{
    void save(int id,String name);
    void delete(int id);
    void update(int id,String name);
}

class UserDaomMysqlImpl implements UserDao{
    public void save(int id, String name) {
        System.out.println("Mysql执行保存！");
    }

    public void delete(int id) {
        System.out.println("Mysql执行删除！");
    }

    public void update(int id, String name) {
        System.out.println("Mysql执行修改！");
    }
}

class UserDaomOracleImpl implements UserDao{
    public void save(int id, String name) {
        System.out.println("Oracle执行保存！");
    }

    public void delete(int id) {
        System.out.println("Oracle执行删除！");
    }

    public void update(int id, String name) {
        System.out.println("Oracle执行修改！");
    }
}

class ProxyFactory{
    private Object obj;

    public ProxyFactory(Object obj){
        super();
        this.obj=obj;
    }

    public Object getTransactionProxyInstance(){
        Object proxy=Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启事务！");
                method.invoke(obj,args);
                System.out.println("关闭事务！");
                return proxy;
            }
        });
        return proxy;
    }

    public Object getLogProxyInstance(){
        Object proxy=Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("开启日志！");
                method.invoke(obj,args);
                System.out.println("关闭日志！");
                return proxy;
            }
        });
        return proxy;
    }
}

class LogHandler implements InvocationHandler{
    Object target;

    public LogHandler(Object target){
        super();
        this.target=target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开启日志。。。");
        method.invoke(target,args);
        System.out.println("关闭日志。。。");
        return proxy;
    }

    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
