package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 * sleep 实现
 */
@Slf4j
public class ConditionRoomDemo {

    public static final Object ROOM = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;
    static ReentrantLock roomLock = new ReentrantLock();
    static Condition waitCigarette = roomLock.newCondition();
    static Condition waitTakeout = roomLock.newCondition();

    public static void main(String[] args) {
        new Thread(() -> {
            roomLock.lock();
            try {
                log.debug("有烟没？{}", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        waitCigarette.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("有烟没？{}", hasCigarette);
                if (hasCigarette) {
                    log.debug("烟到了，可以开始干活了！");
                } else {
                    log.debug("烟没到，不干活了！");
                }
            } finally {
                roomLock.unlock();
            }
        }, "小明").start();

        new Thread(() -> {
            roomLock.lock();
            try {
                log.debug("外卖到了没？{}", hasTakeout);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        waitTakeout.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("外卖到了没？{}", hasTakeout);
                if (hasTakeout) {
                    log.debug("吃到外卖了！");
                } else {
                    log.debug("没吃到外卖，不干活了！");
                }
            } finally {
                roomLock.unlock();
            }
        }, "小红").start();
        sleep(1);
        log.info("=============================================");
        new Thread(() -> {
            roomLock.lock();
            try {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                waitTakeout.signal();
            } finally {
                roomLock.unlock();
            }
        }, "送外卖的").start();
        sleep(1);
        log.info("=============================================");
        new Thread(() -> {
            roomLock.lock();
            try {
                hasCigarette = true;
                log.debug("烟到了噢！");
                waitCigarette.signal();
            } finally {
                roomLock.unlock();
            }
        }, "送烟的").start();

    }


    private static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
