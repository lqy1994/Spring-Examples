package com.meituan.meishi.data.lqy.springexamples.concurrent.waits;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liqingyong02
 * 保护性暂停模式
 */
@Slf4j
public class GuardedObjectDemo {

    public static void main(String[] args) {
        GuardedObject object = new GuardedObject();
        new Thread(() -> {
            log.debug("尝试获取结果:");
            Object o = object.get();
            log.debug("获取到结果: {}", o);
        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载");
            object.complete("res");
        }, "t2").start();
    }

    static class GuardedObject {

        private Object response;

        // 获取结果
        public Object get() {
            synchronized (this) {
                while (response == null) {
                    try {
                        log.debug("等待获取结果");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return response;
            }
        }

        public void complete(Object response) {
            synchronized (this) {
                // 变量赋值
                this.response = response;
                log.debug("执行唤醒");
                this.notifyAll();
            }
        }
    }
}
