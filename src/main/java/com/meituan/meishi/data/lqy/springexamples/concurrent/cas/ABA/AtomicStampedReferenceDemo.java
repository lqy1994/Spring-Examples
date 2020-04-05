package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.ABA;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;

import static com.meituan.meishi.data.lqy.springexamples.utils.SleepUtils.sleep;

/**
 * CAS ABA问题解决
 * @author liqingyong02
 */
@Slf4j
public class AtomicStampedReferenceDemo {

    static AtomicStampedReference<String> ref = new AtomicStampedReference<>("A", 0);

    public static void main(String[] args) {
        String prev = ref.getReference();
        int version = ref.getStamp();
        log.debug("prev: {}, version: {}", prev, version);
        otherOps();
        sleep(1);
        log.debug("change A -> C, res: {}, read version:{}, actual version:{}",
                ref.compareAndSet(prev, "C", version, version + 1),
                version, ref.getStamp());
    }

    private static void otherOps() {
        new Thread(() -> {
            String prev = ref.getReference();
            int version = ref.getStamp();
            log.debug("change A -> B, res: {}", ref.compareAndSet(prev, "B", version, version + 1));
        }, "t1").start();
        sleep(0.5);
        new Thread(() -> {
            String prev = ref.getReference();
            int version = ref.getStamp();
            log.debug("change B -> A, res: {}", ref.compareAndSet(prev, "A", version, version + 1));
        }, "t2").start();
    }

}
