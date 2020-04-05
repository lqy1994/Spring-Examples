package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
public class ConditionDemo {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        lock.lock();

        try {
            condition1.await(100, TimeUnit.MILLISECONDS);
            condition2.await(100, TimeUnit.MILLISECONDS);
        } finally {
            lock.unlock();
        }

    }
}
