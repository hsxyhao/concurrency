package com.yiren.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanghao
 * create 2018-04-30 18:16
 **/
@Slf4j
public class SyncMethodBlockExp {
    public void test1(int index) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1: {}-{}", index,i);
            }
        }
    }

    public synchronized void test2(int index) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test2: {}-{}", index,i);
            }
        }
    }

    public static void main(String[] args){
        SyncMethodBlockExp syncMethodBlockExp1 = new SyncMethodBlockExp();
        SyncMethodBlockExp syncMethodBlockExp2 = new SyncMethodBlockExp();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
//            syncMethodBlockExp.test1();
            syncMethodBlockExp1.test1(1);
        });
        executorService.execute(()->{
//            syncMethodBlockExp.test1();
            syncMethodBlockExp2.test1(2);
        });
    }

}

