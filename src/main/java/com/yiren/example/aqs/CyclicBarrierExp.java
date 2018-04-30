package com.yiren.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * create 2018-04-13 19:55
 **/
@Slf4j
public class CyclicBarrierExp {

    private static CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        // 当突破屏障时，运行
        log.info("callback is running");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.info("msg:{}", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void race(int threadNum) throws InterruptedException {
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
//            barrier.await();
            barrier.await(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.warn("{} BrokenBarrierException", threadNum);
        }
        log.info("{} continue", threadNum);
    }
}
