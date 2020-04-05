package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

import com.meituan.meishi.data.lqy.springexamples.concurrent.cas.unsafe.MyAtomicInteger;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liqingyong02
 */
@Slf4j
public class AccountDemo {

    public static void main(String[] args) {
        int balance = 10_0000;
        Account account1 = new AccountUnsafe(balance);
        demo(account1);
        Account account2 = new AccountSync(balance);
        demo(account2);
        Account account3 = new AccountLock(balance);
        demo(account3);
        Account account4 = new AccountCAS(balance);
        demo(account4);
        Account account5 = new AccountReference(BigDecimal.valueOf(balance));
        demo(account5);
        Account account6 = new MyAtomicInteger(balance);
        demo(account6);
    }

    static void demo(Account account) {
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 1_0000; i++) {
            ts.add(new Thread(() -> {
                account.withDraw(10);
            }));
        }
        long startTime = System.nanoTime();
        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long endTime = System.nanoTime();
        System.out.println(account.getClass().getSimpleName() + "\t " + account.getBalance() + " ---> " + (endTime - startTime) / 1000_000 + "ms");
    }

}
