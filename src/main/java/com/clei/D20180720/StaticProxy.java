package com.clei.D20180720;

import java.util.Random;

public class StaticProxy {
    public static void main(String[] args) {
        Cat c=new Cat();
        CatLogProxy clp=new CatLogProxy(c);
        CatTimeProxy ctp=new CatTimeProxy(clp);
        ctp.eat();
    }
}

interface Eatable{
    void eat();
}

class Cat implements Eatable{
    public void eat() {
        // TODO Auto-generated method stub
        System.out.println("猫吃老鼠！");
    }
}

class CatLogProxy implements Eatable{
    private Eatable e;
    public CatLogProxy(Eatable e) {
        super();
        this.e=e;
    }
    public void eat() {
        // TODO Auto-generated method stub
        System.out.println("Log Start...");
        e.eat();
        try {
            Thread.sleep(new Random().nextInt(1000));
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Log End...");
    }
}

class CatTimeProxy implements Eatable{
    private Eatable e;
    public CatTimeProxy(Eatable e) {
        super();
        this.e=e;
    }
    public void eat() {
        // TODO Auto-generated method stub
        long start=System.currentTimeMillis();
        e.eat();
        long end=System.currentTimeMillis();
        System.out.println("运行时间："+(end-start)+"毫秒！");
    }
}
