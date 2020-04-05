package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
@Slf4j
public class TryLockDemo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("尝试获取锁");
            boolean tryLock = false;
            try {
                tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!tryLock) {
                log.debug("获取不到锁，返回");
                return;
            }

            try {
                log.debug("获取到了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        t1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.debug("主线程释放锁");
            lock.unlock();
        }

    }

}
