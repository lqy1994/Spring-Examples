package com.meituan.meishi.data.lqy.springexamples.concurrent.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liqingyong02
 */
@Slf4j
public class ThreadSafeDemo {

    static AtomicInteger count = new AtomicInteger();
    static volatile int count1 = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.getAndIncrement();
                count1++;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                count.getAndDecrement();
                count1--;
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("counter: {}", count);
        log.debug("counter1: {}", count1);
    }

}
