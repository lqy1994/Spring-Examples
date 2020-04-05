package com.meituan.meishi.data.lqy.springexamples.concurrent.locks;

import lombok.extern.slf4j.Slf4j;

import static com.meituan.meishi.data.lqy.springexamples.utils.SleepUtils.sleep;

/**
 * @author liqingyong02
 */
@Slf4j
public class LiveLockDemo {

    static volatile int count = 0;

    static final Object LOCK = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            // sleep(0.2);
            while (count > 0) {
                count--;
                log.debug("count: {}", count);
            }
        }, "t1").start();
        new Thread(() -> {
            // sleep(0.2);
            while (count < 20) {
                count++;
                log.debug("count: {}", count);
            }
        }, "t2").start();
    }
}
