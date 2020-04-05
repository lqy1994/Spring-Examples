package com.meituan.meishi.data.lqy.springexamples.concurrent.orders;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序执行
 * wait-notify
 *
 * @author liqingyong02
 */
@Slf4j(topic = "OrderDemo2")
public class OrderDemo2 {

    static ReentrantLock lock = new ReentrantLock();
    static boolean t2run = false;
    static Condition t2runCond = lock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                while (!t2run) {
                    try {
                        t2runCond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
            log.debug("t1 run");
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                log.debug("t2 run");
                t2run = true;
                t2runCond.signal();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

}
