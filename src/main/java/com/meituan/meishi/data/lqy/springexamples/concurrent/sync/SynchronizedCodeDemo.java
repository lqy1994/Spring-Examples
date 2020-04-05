package com.meituan.meishi.data.lqy.springexamples.concurrent.sync;

/**
 * @author liqingyong02
 */
public class SynchronizedCodeDemo {

    static Object lock = new Object();
    static int count = 0;

    public static void main(String[] args) {
        synchronized (lock) {
            count++;
        }
    }
}
