package com.yiren.example.concurrent;

import com.yiren.example.BaseExample;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author wanghao
 * create 2018-04-13 14:47
 **/
@Slf4j
public class CowExp extends BaseExample {
    private CopyOnWriteArrayList<Integer> copyOnWrite = new CopyOnWriteArrayList<>();
    private CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
    private ConcurrentSkipListSet<Integer> listSet = new ConcurrentSkipListSet<>();

    @Override
    public void add(int i) {
        copyOnWrite.add(i);
        set.add(i);
        listSet.add(i);
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    protected void runEnd() {
        log.info("CopyOnWriteArrayList:\t {},线程{}", copyOnWrite.size()
                , copyOnWrite.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("CopyOnWriteArraySet:\t {},本次线程{}", set.size()
                , set.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("ConcurrentSkipListSet: \t {},线程{}", listSet.size()
                , listSet.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
    }

    public static void main(String[] args) throws InterruptedException {
        new CowExp().run();
    }
}
