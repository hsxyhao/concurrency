package com.yiren.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author wanghao
 * create 2018-04-12 20:00
 **/
public class ConcurrentModificationExp {

    //ConcurrentModificationException
    public static void iterator(Vector<Integer> vector) {
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(1)) {
                vector.remove(1);
            }
        }
    }

    // ConcurrentModificationException
    public static void foreach(Vector<Integer> vector) {
        for (Integer item : vector) {
            if (item.equals(1)) {
                vector.remove(1);
            }
        }
    }

    //success
    public static void forRemove(Vector<Integer> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if (vector.get(1).equals(1)) {
                vector.remove(1);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(0);
        vector.add(1);
        vector.add(2);
        vector.add(3);
//        iterator(vector);
        foreach(vector);
//        forRemove(vector);
    }

}
