package com.meituan.meishi.data.lqy.springexamples.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.interrupted;
import static java.lang.Thread.sleep;

/**
 * @author liqingyong02
 */
public class InterruptDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterruptDemo.class);

    public static void main(String[] args) throws InterruptedException {
        //sleepInterrupt();
        interruptRunning();
    }

    private static void interruptRunning() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    LOGGER.debug("interrupt-t1, exit");
                    // 优雅的停止线程
                    break;
                }
            }
        }, "t1");
        t1.start();

        Thread.sleep(1000);
        // LOGGER.debug("interrupt-end: {}", t1.isInterrupted());
        t1.interrupt();
    }

    private static void sleepInterrupt() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LOGGER.debug("开始");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("结束");
        }, "t1");
        t1.start();
        LOGGER.debug("interrupt1: {}", t1.isInterrupted());
        Thread.sleep(2000);
        t1.interrupt();
        LOGGER.debug("interrupt2: {}", t1.isInterrupted());
    }
}
