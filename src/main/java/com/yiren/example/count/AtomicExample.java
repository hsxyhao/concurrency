package com.yiren.example.count;

import com.yiren.annoations.ThreadSafe;
import com.yiren.example.BaseExample;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanghao
 * create 2018-04-08 15:16
 **/
@ThreadSafe
public class AtomicExample extends BaseExample {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void add(int i) {
        atomicInteger.incrementAndGet();
    }

    @Override
    public String getCount() {
        return atomicInteger.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        new CustomExample().run();
    }
}
