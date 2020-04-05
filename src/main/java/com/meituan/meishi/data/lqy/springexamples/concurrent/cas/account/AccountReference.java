package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liqingyong02
 */
public class AccountReference implements Account {

    private AtomicReference<BigDecimal> balance;

    public AccountReference(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public void withDraw(Integer amount) {
        while (true) {
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(BigDecimal.valueOf(amount));
            boolean set = balance.compareAndSet(prev, next);
            if (set) {
                break;
            }
        }
    }

    @Override
    public Integer getBalance() {
        return this.balance.get().intValue();
    }
}
