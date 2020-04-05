package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
@Slf4j
public class ReentrantLockDemo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            method1();
        } finally {
            lock.unlock();
        }
    }

    private static void method1() {
        lock.lock();
        try {
            log.debug("method1 lock!");
            method2(); // 可重入
        } finally {
            lock.unlock();
        }
    }

    private static void method2() {
        lock.lock();
        try {
            log.debug("method2 lock!");
            method3();
        } finally {
            lock.unlock();
        }
    }

    private static void method3() {
        lock.lock();
        try {
            log.debug("method3 lock!");
        } finally {
            lock.unlock();
        }
    }

}
