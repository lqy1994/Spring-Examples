package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.thinkers;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
@Data
@Slf4j
public class Philosopher extends Thread {

    private Chopstick left;

    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        // synchronizedEat();
        reentrantLockEat();
    }

    private void reentrantLockEat() {
        while (true) {
            if (left.tryLock()) {
                try {
//                    log.debug(getName() + "拿到了左筷子：" + left.getName());
                    if (right.tryLock()) {
                        try {
//                            log.debug(getName() + "拿到了右筷子：" + right.getName());
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void synchronizedEat() {
        while (true) {
            synchronized (left) {
                log.debug(getName() + "拿到了筷子：" + left.getName());
                synchronized (right) {
                    log.debug(getName() + "拿到了筷子：" + right.getName());
                    eat();
                }
            }
        }
    }

    private void eat() {
        log.debug("吃饭了：" + getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
