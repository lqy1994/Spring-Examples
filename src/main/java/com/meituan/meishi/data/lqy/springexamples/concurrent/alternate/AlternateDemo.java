package com.meituan.meishi.data.lqy.springexamples.concurrent.alternate;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

/**
 * 线程1输出a 5次
 * 线程2输出b 5次
 * 线程3输出c 5次
 * 现在要输出abcabcabcabcabc
 *
 * @author liqingyong02
 */
@Slf4j(topic = "AlternateDemo")
public class AlternateDemo {

    public static void main(String[] args) throws InterruptedException {
        waitNotifyDemo();
        Thread.sleep(1000);
        System.out.println();
        awaitSignalDemo();
        Thread.sleep(1000);
        System.out.println();
        parkUnParkDemo();
    }

    private static void waitNotifyDemo() {
        // 输出内容 等待标记 下一标记
        WaitNotify waitNotify = new WaitNotify(1, 5);
        new Thread(() -> {
            waitNotify.print("a", 1, 2);
        }, "t1").start();

        new Thread(() -> {
            waitNotify.print("b", 2, 3);
        }, "t2").start();

        new Thread(() -> {
            waitNotify.print("c", 3, 1);
        }, "t3").start();
    }

    private static void awaitSignalDemo() {
        AwaitSignal lock = new AwaitSignal(5);
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        new Thread(() -> {
            lock.print("a", a,  b);
        }, "t1").start();

        new Thread(() -> {
            lock.print("b", b, c);
        }, "t2").start();

        new Thread(() -> {
            lock.print("c", c, a);
        }, "t3").start();

        lock.lock();
        try {
             a.signal();
        } finally {
            lock.unlock();
        }
    }

    static Thread t1;
    static Thread t2;
    static Thread t3;

    private static void parkUnParkDemo() {
        ParkUnPark parkUnPark = new ParkUnPark(5);
        t1 = new Thread(() -> {
            parkUnPark.print("a", t2);
        }, "t1");

        t2 = new Thread(() -> {
            parkUnPark.print("b", t3);
        }, "t2");

        t3 = new Thread(() -> {
            parkUnPark.print("c", t1);
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        // 唤醒t1
        LockSupport.unpark(t1);
    }

}
