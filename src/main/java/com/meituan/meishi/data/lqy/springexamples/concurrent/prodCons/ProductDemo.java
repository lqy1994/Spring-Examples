package com.meituan.meishi.data.lqy.springexamples.concurrent.prodCons;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import static com.meituan.meishi.data.lqy.springexamples.utils.SleepUtils.sleep;

/**
 * @author liqingyong02
 */
@Slf4j
public class ProductDemo {

    public static void main(String[] args) {
        final MessageQueue queue = new MessageQueue();
        queue.setCapacity(5);
        for (int i = 0; i < 10; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "消息值" + id));
            }, "生产者" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                sleep(1);
                Message msg = queue.take();
            }, "消费者" + i).start();
        }
    }

}
