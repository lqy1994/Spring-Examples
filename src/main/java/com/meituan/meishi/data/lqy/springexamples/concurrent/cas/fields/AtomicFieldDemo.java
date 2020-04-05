package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.fields;

import com.meituan.meishi.data.lqy.springexamples.concurrent.cas.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author liqingyong02
 */
@Slf4j
public class AtomicFieldDemo {


    public static void main(String[] args) {
        Student student = new Student();
        AtomicReferenceFieldUpdater<Student, String> updater =
                AtomicReferenceFieldUpdater.newUpdater(Student.class, String.class, "name");
        boolean set = updater.compareAndSet(student, null, "张三");
        System.out.println(set + "\t" + updater.get(student));
    }

}
