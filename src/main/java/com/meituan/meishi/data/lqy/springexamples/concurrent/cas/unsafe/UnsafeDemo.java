package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.unsafe;

import lombok.Data;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

import static com.meituan.meishi.data.lqy.springexamples.concurrent.cas.unsafe.UnsafeAccessor.unsafe;

/**
 * @author liqingyong02
 */
public class UnsafeDemo {



    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = UnsafeAccessor.getUnsafe();
        //1 获取域的偏移地址
        Field id = Teacher.class.getDeclaredField("id");
        Field name = Teacher.class.getDeclaredField("name");

        long idOffset = UnsafeAccessor.unsafe.objectFieldOffset(id);
        long nameOffset = UnsafeAccessor.unsafe.objectFieldOffset(name);
        //2 执行unsafe操作
        Teacher teacher = new Teacher();

        UnsafeAccessor.unsafe.compareAndSwapInt(teacher, idOffset, 0, 24);
        UnsafeAccessor.unsafe.compareAndSwapObject(teacher, nameOffset, null, "孔子");
        System.out.println(teacher);
    }

    @Data
    static class Teacher {
        volatile int id;
        volatile String name;
    }

}
