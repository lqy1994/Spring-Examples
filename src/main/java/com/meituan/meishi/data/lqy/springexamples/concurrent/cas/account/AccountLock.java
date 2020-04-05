package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liqingyong02
 */
public class AccountLock implements Account {

    private static ReentrantLock lock = new ReentrantLock();
    private Integer balance;

    public AccountLock(Integer balance) {
        this.balance = balance;
    }

    @Override
    public void withDraw(Integer amount) {
        lock.lock();
        try {
            this.balance -= amount;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Integer getBalance() {
        lock.lock();
        try {
            return this.balance;
        } finally {
            lock.unlock();
        }
    }

}
