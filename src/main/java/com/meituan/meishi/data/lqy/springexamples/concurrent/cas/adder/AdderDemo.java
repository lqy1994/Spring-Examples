package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.adder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * LongAdder 累加器性能比AtomicInteger更好
 * @see LongAdder 多个累加单元 cells
 *
 * @author liqingyong02
 */
public class AdderDemo {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            demo(
                    () -> new AtomicInteger(0),
                    AtomicInteger::getAndIncrement
            );
        }
        System.out.println("=================================");
        for (int i = 0; i < 20; i++) {
            demo(
                    LongAdder::new,
                    LongAdder::increment
            );
        }

    }

    private static <T> void demo(Supplier<T> adderSupply, Consumer<T> action) {
        T adder = adderSupply.get();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ts.add(new Thread(() -> {
                for (int j = 0; j < 100_0000; j++) {
                    action.accept(adder);
                }
            }));
        }

        long start = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.nanoTime();
        System.out.println("adder: " + adder + " --> " + (end - start) / 1000_000 + "ms" + " ---> " + adder.getClass().getSimpleName());
    }

}
