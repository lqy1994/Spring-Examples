package com.meituan.meishi.data.lqy.springexamples.concurrent.orders;

import lombok.extern.slf4j.Slf4j;

/**
 * 顺序执行
 * wait-notify
 *
 * @author liqingyong02
 */
@Slf4j(topic = "OrderDemo1")
public class OrderDemo1 {

    static Object lock = new Object();
    static boolean t2run = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (!t2run) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.debug("t1 run");
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.debug("t2 run");
                t2run = true;
                lock.notify();
            }
        }, "t2").start();
    }

}
