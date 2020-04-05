package com.meituan.meishi.data.lqy.springexamples.concurrent.waits;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liqingyong02
 * sleep 实现
 */
@Slf4j
public class SleepRoomDemo {

    public static final Object ROOM = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (ROOM) {
                log.debug("有烟没？{}", hasCigarette);
                if (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    sleep(2);
                }
                log.debug("有烟没？{}", hasCigarette);
                if (hasCigarette) {
                    log.debug("可以开始干活了！");
                }
            }
        }, "小明").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (ROOM) {
                    log.debug("可以开始干活了！");
                }
            }, "其他人").start();
        }

        sleep(1);
        new Thread(() -> {
//            synchronized (ROOM) {
                hasCigarette = true;
                log.debug("烟到了噢！");
//            }
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
