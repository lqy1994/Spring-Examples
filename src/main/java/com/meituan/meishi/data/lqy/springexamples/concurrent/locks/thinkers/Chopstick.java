package com.meituan.meishi.data.lqy.springexamples.concurrent.locks.thinkers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
@Data
@AllArgsConstructor
public class Chopstick extends ReentrantLock {

    private String name;

    public Chopstick(boolean fair, String name) {
        super(fair);
        this.name = name;
    }
}
