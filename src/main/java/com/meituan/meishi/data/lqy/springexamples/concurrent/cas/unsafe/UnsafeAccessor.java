package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author liqingyong02
 */
public class UnsafeAccessor {

    /**
     * 不安全指的是：会操作内存造成不安全
     */
    static Unsafe unsafe;

    public static Unsafe getUnsafe() {
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        assert theUnsafe != null;
        theUnsafe.setAccessible(true);
        // static属性不需要传对象
        try {
            unsafe = (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return unsafe;
    }
}
