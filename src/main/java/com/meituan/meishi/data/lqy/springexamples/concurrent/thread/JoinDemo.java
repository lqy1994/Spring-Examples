package com.meituan.meishi.data.lqy.springexamples.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

/**
 * 线程同步
 * @author liqingyong02
 */
public class JoinDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoinDemo.class);

    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LOGGER.debug("开始");
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.debug("结束");
            r = 10;
        }, "t1");
        t1.start();
        t1.join(); // 等待线程1执行完成
        LOGGER.debug("结果：{}", r);
        LOGGER.debug("END");
    }


}
