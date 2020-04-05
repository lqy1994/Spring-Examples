package com.meituan.meishi.data.lqy.springexamples.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author liqingyong02
 */
public class SleepUtils {

    public static void sleep(double second) {
        try {
            TimeUnit.MILLISECONDS.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
