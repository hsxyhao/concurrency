package com.yiren.example.commonUnsafe;

import com.yiren.annoations.ThreadSafe;
import com.yiren.example.BaseExample;

/**
 * @author wanghao
 * create 2018-04-11 19:22
 **/
@ThreadSafe
public class StringBufferExp extends BaseExample {

    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    public void add(int i) {
        stringBuffer.append("1");
    }

    @Override
    public String getCount() {
        return String.valueOf(stringBuffer.length());
    }

    public static void main(String[] args) throws InterruptedException {
        new StringBufferExp().run();
    }

}
