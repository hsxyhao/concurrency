package com.yiren.example.threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author wanghao
 * create 2018-04-09 16:59
 **/
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        // 延时执行
        executorService.schedule(() -> {
            System.out.println("schedule run");
        }, 3, TimeUnit.SECONDS);

        // 定期执行
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("scheduleAtFixedRate run:"+System.currentTimeMillis());
        }, 3, 5, TimeUnit.SECONDS);

        // executorService.shutdown();
    }

}
