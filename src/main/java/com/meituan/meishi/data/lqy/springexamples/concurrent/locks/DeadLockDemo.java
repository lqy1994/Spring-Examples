package com.meituan.meishi.data.lqy.springexamples.concurrent.locks;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liqingyong02
 */@Slf4j
public class DeadLockDemo {

    private static final Object A = new Object();

    private static final Object B = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (A) {
                log.debug("t1 locked A");
                synchronized (B) {
                    log.debug("t1 lock B");

                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (B) {
                log.debug("t2 locked B");
                synchronized (A) {
                    log.debug("t2 lock A");

                }
            }
        }, "t2").start();
    }

}
