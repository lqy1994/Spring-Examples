package com.meituan.meishi.data.lqy.springexamples.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liqingyong02
 */
@Slf4j
public class SynchronizedDemo {

    static final Object OBJECT = new Object();
    static int count1 = 0;

    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(() -> {
//            for (int i = 0; i < 100000; i++) {
//                synchronized (OBJECT) {
//                    count1++;
//                }
//            }
            room.increment();
        });
        Thread t2 = new Thread(() -> {
//            for (int i = 0; i < 100000; i++) {
//                synchronized (OBJECT) {
//                    count1--;
//                }
//            }
            room.decrement();
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

//        log.debug("counter1: {}", count1);
        log.debug("counter1: {}", room.getCount());
    }

    static class Room {
        private int count = 0;

        public void increment() {
            synchronized (this) {
                count++;
            }
        }

        public void decrement() {
            synchronized (this) {
                count--;
            }
        }

        public int getCount() {
            synchronized (this) {
                return count;
            }
        }
    }
}

