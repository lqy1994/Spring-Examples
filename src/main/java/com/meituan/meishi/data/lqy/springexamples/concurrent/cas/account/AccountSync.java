package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

/**
 * @author liqingyong02
 */
public class AccountSync implements Account {

    private Integer balance;

    public AccountSync(Integer balance) {
        this.balance = balance;
    }

    @Override
    public void withDraw(Integer amount) {
        synchronized (this) {
            this.balance -= amount;
        }
    }

    @Override
    public Integer getBalance() {
        synchronized (this) {
            return this.balance;
        }
    }

}
