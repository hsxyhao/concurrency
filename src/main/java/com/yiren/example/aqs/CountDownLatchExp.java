package com.yiren.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanghao
 * create 2018-04-13 18:07
 **/
@Slf4j
public class CountDownLatchExp {
    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    printNum(threadNum);
                } catch (InterruptedException e) {
                    log.error("msg:{}", e.getMessage());
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finish");
        exec.shutdown();//关闭线程池
    }

    private static void printNum(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("num: {}", threadNum);
        Thread.sleep(100);
    }

}
