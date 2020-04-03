package com.meituan.meishi.data.lqy.springexamples.concurrent.thread;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liqingyong02
 */
public class StateDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateDemo.class);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            LOGGER.debug("t3 run...");
        }, "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (StateDemo.class) {
                try {
                    Thread.sleep(100000); // timed_waiting -- 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t2.join(); // waiting -- 阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (StateDemo.class) {
                try {
                    Thread.sleep(100000); // blocked -- 阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        LOGGER.debug("t1 --> {}", t1.getState()); // NEW
        LOGGER.debug("t2 --> {}", t2.getState()); // RUNNABLE
        LOGGER.debug("t3 --> {}", t3.getState()); // TERMINATED
        LOGGER.debug("t4 --> {}", t4.getState()); // TIMED_WAITING
        LOGGER.debug("t5 --> {}", t5.getState()); // WAITING
        LOGGER.debug("t6 --> {}", t6.getState()); // BLOCKED
    }
}
