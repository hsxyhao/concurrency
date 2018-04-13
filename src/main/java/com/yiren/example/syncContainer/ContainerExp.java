package com.yiren.example.syncContainer;

import com.google.common.collect.Lists;
import com.yiren.example.BaseExample;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author wanghao
 * create 2018-04-12 18:26
 **/
@Slf4j
public class ContainerExp extends BaseExample {

    private HashMap<Integer, Integer> map;
    private Hashtable<Integer, Integer> table;
    private ArrayList<Integer> list;
    private Vector<Integer> vector;
    private List<Integer> syncList = Collections.synchronizedList(Lists.newArrayList());

    public ContainerExp() {
        map = new HashMap<>();
        table = new Hashtable<>();
        list = new ArrayList<>();
        vector = new Vector<>();
    }

    @Override
    public void add(int i) {
        map.put(i, i);
        table.put(i, i);
        list.add(i);
        vector.add(i);
        syncList.add(i);
    }

    @Override
    protected void runEnd() {
        log.info("HashMap:\t {},本次线程{}", map.size()
                , map.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("Hashtable:\t {},线程{}", table.size()
                , table.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("ArrayList:\t {},本次线程{}", list.size()
                , list.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("Vector: \t {},线程{}", vector.size()
                , vector.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
        log.info("SyncList:\t {},线程{}", syncList.size()
                , syncList.size() == BaseExample.clientTotal ? "安全【✔】" : "不安全【✘】");
    }

    @Override
    public String getCount() {
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        new ContainerExp().run();
    }
}
