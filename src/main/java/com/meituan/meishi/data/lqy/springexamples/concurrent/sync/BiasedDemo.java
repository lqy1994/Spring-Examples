package com.meituan.meishi.data.lqy.springexamples.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

/**
 * @author liqingyong02
 * -XX:BiasedLockingStartupDelay=0
 */
public class BiasedDemo {

    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog();
        System.out.println(Thread.currentThread().getId());
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
        synchronized (dog) {
            System.out.println(ClassLayout.parseInstance(dog).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

    static class Dog {

    }
}
