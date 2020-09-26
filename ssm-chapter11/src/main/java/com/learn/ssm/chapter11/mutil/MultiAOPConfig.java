package com.learn.ssm.chapter11.mutil;

import com.learn.ssm.chapter11.mutil.aspect.Asepect1;
import com.learn.ssm.chapter11.mutil.aspect.Asepect2;
import com.learn.ssm.chapter11.mutil.aspect.Asepect3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.learn.ssm.chapter11.mutil")
public class MultiAOPConfig {

    @Bean
    public Asepect1 getAsepect1(){
        return new Asepect1();
    }
    @Bean
    public Asepect2 getAsepect2(){
        return new Asepect2();
    }
    @Bean
    public Asepect3 getAsepect3(){
        return new Asepect3();
    }

}
