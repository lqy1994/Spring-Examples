package com.meituan.meishi.data.lqy.springexamples.concurrent.prodCons;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @author liqingyong02
 */
@Data
@Slf4j
public class MessageQueue {

    private final LinkedList<Message> list = new LinkedList<>();

    private int capacity;

    public Message take() {
        synchronized (list) {
            while (list.isEmpty()) {
                try {
                    log.debug("队列为空，消费者线程等待！");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.notifyAll(); // 唤醒正在添加元素的线程
            Message message = list.removeFirst();
            log.debug("消费者，已消费消息{}！", message);
            return message;
        }
    }

    public void put(Message msg) {
        synchronized (list) {
            while (list.size() == capacity) {
                try {
                    log.debug("队列已满，生产者线程等待！");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(msg);
            log.debug("生产者，已生产消息{}！", msg);
            list.notifyAll(); // 唤醒正在获取元素的线程
        }
    }

}
