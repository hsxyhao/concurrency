package com.yiren.example.count;

import com.yiren.annoations.NotThreadSafe;
import com.yiren.example.BaseExample;

/**
 * 常见的写法
 * @author wanghao
 * create 2018-04-08 13:59
 **/
@NotThreadSafe
public class CustomExample extends BaseExample {

    private static int count = 0;

    @Override
    public void add(int i) {
        count++;
    }

    @Override
    public String getCount() {
        return String.valueOf(count);
    }

    public static void main(String[] args) throws InterruptedException {
        new CustomExample().run();
    }
}
