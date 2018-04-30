package com.yiren.example.lock;

import com.yiren.example.BaseExample;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanghao
 * create 2018-04-29 17:07
 **/
public class ReentrantLockExp extends BaseExample {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void add(int i) {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }
    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockExp().run();
    }
}
