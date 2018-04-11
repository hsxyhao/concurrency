package com.yiren.example.threadLocal;

/**
 * @author wanghao
 * create 2018-04-11 18:00
 **/
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

}
