package com.meituan.meishi.data.lqy.springexamples.concurrent.prodCons;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

import static com.meituan.meishi.data.lqy.springexamples.utils.SleepUtils.sleep;

/**
 * @author liqingyong02
 */
@Slf4j
public class ParkDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("开始...");
            sleep(2);
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();

        sleep(1);
        log.debug("unPark...");
        LockSupport.unpark(t1); // 可以在线程停止前调用unpark
    }

}
