package com.meituan.meishi.data.lqy.springexamples.concurrent.orders;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序执行
 * wait-notify
 *
 * @author liqingyong02
 */
@Slf4j(topic = "OrderDemo3")
public class OrderDemo3 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("t1 run");
        }, "t1");

        Thread t2 = new Thread(() -> {
            log.debug("t2 run");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }

}
