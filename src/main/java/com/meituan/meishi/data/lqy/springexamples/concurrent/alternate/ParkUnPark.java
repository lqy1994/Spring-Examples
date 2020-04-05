package com.meituan.meishi.data.lqy.springexamples.concurrent.alternate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.LockSupport;

/**
 * @author liqingyong02
 */
@Data
@AllArgsConstructor
public class ParkUnPark {

    private int loopNum;

    public void print(String str, Thread next) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.print(str);
            // 唤醒下一个线程
            LockSupport.unpark(next);
        }
    }
}
