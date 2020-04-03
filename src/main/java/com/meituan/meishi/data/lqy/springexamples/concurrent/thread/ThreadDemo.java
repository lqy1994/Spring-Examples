package com.meituan.meishi.data.lqy.springexamples.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author liqingyong02
 */
public class ThreadDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadDemo.class);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
//                Thread.sleep(2000);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        LOGGER.info("t1 state:{}", t1.getState());
        t1.start();
        LOGGER.info("t1 state:{}", t1.getState());
        Thread.sleep(500);
        LOGGER.info("t1 state:{}", t1.getState());
        t1.interrupt();
        LOGGER.info("t1 state:{}", t1.getState());
    }
}
