package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.unsafe;

import com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account.Account;
import sun.misc.Unsafe;

/**
 * @author liqingyong02
 * @see java.util.concurrent.atomic.AtomicInteger
 */
public class MyAtomicInteger implements Account {

    private volatile int value;
    private static long valueOffset;
    private static final Unsafe UNSAFE;

    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
            valueOffset = UNSAFE.objectFieldOffset(
                    MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void decrement(int amount) {
        while (true) {
            int prev = this.value;
            int next = this.value - amount;
            boolean set = UNSAFE.compareAndSwapInt(this, valueOffset, prev, next);
            if (set) {
                break;
            }
        }
    }

    @Override
    public void withDraw(Integer amount) {
        decrement(amount);
    }

    @Override
    public Integer getBalance() {
        return getValue();
    }

}
