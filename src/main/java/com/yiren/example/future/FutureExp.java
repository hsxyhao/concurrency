package com.yiren.example.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author wanghao
 * create 2018-04-29 20:33
 **/
@Slf4j
public class FutureExp {

    static class MyFuture implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(new MyFuture());
        log.info("do something in main");
        Thread.sleep(1000);
        String data = result.get();
        log.info("result:{}", data);
        executorService.shutdown();
    }
}
