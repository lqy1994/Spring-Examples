package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.thinkers;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liqingyong02
 */
@Slf4j
public class ThinkerDemo {

    public static void main(String[] args) {
        Chopstick c1 = new Chopstick(true, "1");
        Chopstick c2 = new Chopstick(true, "2");
        Chopstick c3 = new Chopstick(true, "3");
        Chopstick c4 = new Chopstick(true, "4");
        Chopstick c5 = new Chopstick(true, "5");
        Philosopher p1 = new Philosopher("苏格拉底", c1, c2);
        Philosopher p2 = new Philosopher("亚里士多德", c2, c3);
        Philosopher p3 = new Philosopher("柏拉图", c3, c4);
        Philosopher p4 = new Philosopher("王守仁", c4, c5);
        // 死锁
        Philosopher p5 = new Philosopher("孔子", c5, c1);
        // 有线程饥饿
//        Philosopher p5 = new Philosopher("孔子", c1, c5);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
