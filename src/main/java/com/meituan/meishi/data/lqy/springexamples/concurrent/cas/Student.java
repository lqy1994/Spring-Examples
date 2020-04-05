package com.meituan.meishi.data.lqy.springexamples.concurrent.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqingyong02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    public volatile String name;

}
