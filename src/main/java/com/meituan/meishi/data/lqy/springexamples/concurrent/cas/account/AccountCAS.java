package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liqingyong02
 */
public class AccountCAS implements Account {

    private AtomicInteger balance;

    public AccountCAS(Integer balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override
    public void withDraw(Integer amount) {
        while (true) {
            // 获取余额最新值
            int prev = balance.get();
            // 要修改的余额
            int next = prev - amount;
            // 修改到主存
            boolean set = balance.compareAndSet(prev, next);
            if (set) {
                break;
            }
        }
    }

    @Override
    public Integer getBalance() {
        return this.balance.get();
    }

}
