package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

/**
 * @author liqingyong02
 */
public class AccountUnsafe implements Account {

    private Integer balance;

    public AccountUnsafe(Integer balance) {
        this.balance = balance;
    }

    @Override
    public void withDraw(Integer amount) {
        this.balance -= amount;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

}
