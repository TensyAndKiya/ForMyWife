package com.clei.D20181108;

public class AddOverload<T extends Number> {
    public static void main(String[] args){
        AddOverload<Integer> aaa=new AddOverload<>();
        int result=(int)aaa.add(3,4);
        System.out.println(result);

        TTT tt=(x,y)->x+y;
        tt.f1(3,4);
    }

    public double add(T a,T b){
        return  a.doubleValue()+b.doubleValue();
    }
}

@FunctionalInterface
interface TTT{
    default int f1(int x,int y){
        return x*y;
    }

    int f2(int x,int y);
}
