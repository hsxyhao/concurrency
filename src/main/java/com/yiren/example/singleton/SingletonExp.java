package com.yiren.example.singleton;

import com.yiren.annoations.NotRecommend;
import com.yiren.annoations.NotThreadSafe;
import com.yiren.annoations.ThreadSafe;

/**
 * @author wanghao
 * create 2018-04-10 14:45
 **/
public class SingletonExp {
}

class CustomSingleton {
    private CustomSingleton() {
        System.out.println("单例【懒汉】模式");
    }

    // 使用volatile防止指令重排序
    private static volatile CustomSingleton customSingleton = null;

    //线程不安全
    @NotRecommend
    @NotThreadSafe
    public static CustomSingleton newInstance() {
        if (customSingleton == null) {
            customSingleton = new CustomSingleton();
        }
        return customSingleton;
    }

    @ThreadSafe
    @NotRecommend
    // 性能降低
    public static synchronized CustomSingleton syncNewInstance1() {
        if (customSingleton == null) {
            customSingleton = new CustomSingleton();
        }
        return customSingleton;
    }

    // 1、memory = allocate()分配对象内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存

    // 指令重排
    // 1、memory = allocate()分配对象内存空间
    // 3、instance = memory 设置instance指向刚分配的内存
    // 2、ctorInstance() 初始化对象

    @NotThreadSafe
    @NotRecommend
    public static CustomSingleton syncNewInstance2() {
        // 指令重排序会造成这部返回false
        if (customSingleton == null) {// 双重检测机制
            synchronized (CustomSingleton.class) { // 同步锁
                if (customSingleton == null) {
                    customSingleton = new CustomSingleton();
                }
            }
        }
        return customSingleton;
    }
}

/**
 * 线程安全，但是在类装载的时候如果进行大量的操作会引起性能问题，以及如果这个类只被装在却不被使用会造成资源浪费
 */
class HungrySingleton {
    private HungrySingleton() {
        System.out.println("饿汉模式");
    }

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    //线程安全
    public static HungrySingleton newInstance() {
        return hungrySingleton;
    }
}
