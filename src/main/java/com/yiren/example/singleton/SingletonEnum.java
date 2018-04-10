package com.yiren.example.singleton;

import com.yiren.annoations.Recommend;
import com.yiren.annoations.ThreadSafe;

/**
 * 枚举单例模式，线程安全而且还不会造成资源的浪费
 * @author wanghao
 * create 2018-04-10 15:43
 **/
@ThreadSafe
@Recommend
public class SingletonEnum {

    public static SingletonEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private SingletonEnum singleton;

        Singleton() {
            singleton = new SingletonEnum();
        }

        public SingletonEnum getInstance() {
            return singleton;
        }
    }
}
