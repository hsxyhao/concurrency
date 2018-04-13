package com.yiren.example.commonUnsafe;

import com.yiren.annoations.NotRecommend;
import com.yiren.annoations.NotThreadSafe;
import com.yiren.example.BaseExample;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wanghao
 * create 2018-04-11 19:00
 **/
@NotThreadSafe
@NotRecommend
public class StringBuilderExp extends BaseExample {

    private StringBuilder str = new StringBuilder();

    @Override
    public void add(int i) {
        str.append("1");
    }

    @Override
    public String getCount() {
        return String.valueOf(str.length());
    }

    public static void main(String[] args) throws InterruptedException {
        new StringBuilderExp().run();
    }
}
