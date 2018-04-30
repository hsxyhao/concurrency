package com.yiren.example.lock;

import com.yiren.example.BaseExample;

/**
 * @author wanghao
 * create 2018-04-29 17:03
 **/
public class SyncLockExp extends BaseExample {

    private int count = 0;

    @Override
    public void add(int i) {
        synchronized (SyncLockExp.class) {
            count++;
        }
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }
    public static void main(String[] args) throws InterruptedException {
        new SyncLockExp().run();
    }
}
