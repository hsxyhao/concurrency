package com.yiren.example.sync;

import com.yiren.example.BaseExample;

/**
 * @author wanghao
 * create 2018-04-30 18:31
 **/
public class SyncCountExp extends BaseExample {

    private int count = 0;

    @Override
    public void add(int i) {
        synchronized (this) {
            count++;
        }
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }
    public static void main(String[] args) throws InterruptedException {
        new SyncCountExp().run();
    }
}
