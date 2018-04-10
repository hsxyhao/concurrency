package com.yiren.example.lock;

/**
 * @author wanghao
 * create 2018-04-09 19:11
 **/
public class DeadLock implements Runnable {

    private static Object o1 = new Object();
    private static Object o2 = new Object();

    private int flag;

    public DeadLock(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag == 0) {
                synchronized (o1) {
                    Thread.sleep(1000);
                    synchronized (o2) {
                        System.out.println(flag);
                    }
                }
            } else {
                synchronized (o2) {
                    Thread.sleep(1000);
                    synchronized (o1) {
                        System.out.println(flag);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new DeadLock(0));
        Thread t2 = new Thread(new DeadLock(1));
        t1.start();
        t2.start();
    }
}
