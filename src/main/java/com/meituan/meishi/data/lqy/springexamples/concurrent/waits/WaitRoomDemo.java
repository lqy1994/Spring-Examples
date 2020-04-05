package com.meituan.meishi.data.lqy.springexamples.concurrent.waits;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liqingyong02
 * sleep 实现
 */
@Slf4j
public class WaitRoomDemo {

    public static final Object ROOM = new Object();
    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (ROOM) {
                log.debug("有烟没？{}", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        ROOM.wait();
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
            }
        }, "小明").start();

        new Thread(() -> {
            synchronized (ROOM) {
                log.debug("外卖到了没？{}", hasTakeout);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        ROOM.wait();
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
            }
        }, "小红").start();

        sleep(1);
        new Thread(() -> {
            synchronized (ROOM) {
                hasTakeout = true;
                log.debug("外卖到了噢！");
                ROOM.notifyAll();
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
