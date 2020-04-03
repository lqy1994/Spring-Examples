package com.meituan.meishi.data.lqy.springexamples.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liqingyong02
 */
public class YieldDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(YieldDemo.class);

    public static void main(String[] args) {
        Runnable task1 = () -> {
            int count = 0;
            while (true) {
                LOGGER.info("task1 ---> {}", count++);
            }
        };
        Runnable task2 = () -> {
            int count = 0;
            while (true) {
                Thread.yield();
                LOGGER.info("task2 ---> {}", count++);
            }
        };

        new Thread(task1, "task1").start();
        new Thread(task2, "task2").start();
    }
}
