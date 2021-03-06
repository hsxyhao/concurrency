package com.yiren.example.syncContainer;

import com.yiren.annoations.NotThreadSafe;

import java.util.Vector;

/**
 * @author wanghao
 * create 2018-04-12 18:57
 **/
@NotThreadSafe
public class VectorNotSafe {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {


        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            };


            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }

    }

}
