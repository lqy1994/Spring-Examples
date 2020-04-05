package com.meituan.meishi.data.lqy.springexamples.concurrent.prodCons;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author liqingyong02
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class Message {

    private int id;

    private Object value;

}
