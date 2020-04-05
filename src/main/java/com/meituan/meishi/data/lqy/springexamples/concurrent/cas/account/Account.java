package com.meituan.meishi.data.lqy.springexamples.concurrent.cas.account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liqingyong02
 */
public interface Account {

    /**
     * 取款
     *
     * @param amount 取款额
     */
    void withDraw(Integer amount);

    /**
     * 获取余额
     *
     * @return 余额
     */
    Integer getBalance();

}
