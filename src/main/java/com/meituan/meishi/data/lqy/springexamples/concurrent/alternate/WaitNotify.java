package com.meituan.meishi.data.lqy.springexamples.concurrent.alternate;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liqingyong02
 */
@Data
@AllArgsConstructor
public class WaitNotify {
    /**
     * 等待标记
     */
    private int flag;
    /**
     * 循环次数
     */
    private int loopNum;

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (this.flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                this.flag = nextFlag;
                this.notifyAll();
            }
        }
    }

}
