package com.yiren.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanghao
 * create 2018-04-30 18:27
 **/
@Slf4j
public class SyncClassExp {
    public static synchronized void test1(int index) {
        for (int i = 0; i < 10; i++) {
            log.info("test1: {}-{}", index,i);
        }
    }
    public void test2(int index) {
        synchronized (SyncClassExp.class){
            for (int i = 0; i < 10; i++) {
                log.info("test1: {}-{}", index,i);
            }
        }
    }
    public static void main(String[] args){
        SyncClassExp syncClassExp1 = new SyncClassExp();
        SyncClassExp syncClassExp2 = new SyncClassExp();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            syncClassExp1.test2(1);
        });
        executorService.execute(()->{
            syncClassExp2.test2(2);
        });
    }
}
