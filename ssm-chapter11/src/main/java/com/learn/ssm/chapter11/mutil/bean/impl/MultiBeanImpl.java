package com.learn.ssm.chapter11.mutil.bean.impl;

import com.learn.ssm.chapter11.mutil.bean.MutilBean;
import org.springframework.stereotype.Component;

@Component
public class MultiBeanImpl implements MutilBean {
    @Override
    public void testMulti() {
        System.out.println("test multi aspects");
    }
}
