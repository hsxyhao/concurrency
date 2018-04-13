package com.yiren.example;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author wanghao
 * create 2018-04-08 15:18
 **/
@Slf4j
public abstract class BaseExample {
    public final static int clientTotal = 5000;
    private final static int threadTotal = 20;

    public void run() throws InterruptedException {
        Long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int var1 = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(var1);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("error:" + e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        String count = getCount();
        if (count != null) {
            log.info("count:{}", count);
        }
        Long end = System.currentTimeMillis();
        log.info("time:{}", end - start);
        runEnd();
    }

    public abstract void add(int i);

    public abstract String getCount();

    protected void runEnd() {

    }

}
