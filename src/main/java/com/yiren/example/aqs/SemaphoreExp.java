package com.yiren.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wanghao
 * create 2018-04-13 18:54
 **/
@Slf4j
public class SemaphoreExp {


    private final static int threadCount = 20;

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    printNum(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.info("msg:{}", e.getMessage());
                }
            });
        }
    }

    private static void printNum(int threadNum) throws InterruptedException {
        log.info("num:{}", threadNum);
        Thread.sleep(1000);
    }

}
