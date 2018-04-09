package com.yiren.example.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wanghao
 * create 2018-04-09 16:30
 **/
public class SingleThreadPoolExp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(() -> {
                System.out.println("task:" + index + ",name:" + Thread.currentThread().getName());
            });
        }
        // 关闭线程池，否则程序不会终止
        executorService.shutdown();
    }

}
