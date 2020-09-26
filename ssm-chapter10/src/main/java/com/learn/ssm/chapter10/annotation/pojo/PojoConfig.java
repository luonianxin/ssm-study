package com.learn.ssm.chapter10.annotation.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PojoConfig {


    @Bean(name = "juiceMaker2",initMethod = "init" ,destroyMethod = "destroy")
    public JuiceMaker2 initJuiMaker2(){
        JuiceMaker2 juiceMaker2 = new JuiceMaker2();
        juiceMaker2.setBeverageShop("贡茶");
        Source source = new Source();
        source.setFruit("香蕉");
        source.setSize("大杯");
        source.setSugar("少量糖");
        juiceMaker2.setSource(source);
        return juiceMaker2;
    }
}
