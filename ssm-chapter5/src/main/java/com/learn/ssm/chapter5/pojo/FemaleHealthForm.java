package com.learn.ssm.chapter5.pojo;

public class FemaleHealthForm extends HealthForm {
    private String uterus;

    public String getUterus() {
        return uterus;
    }

    @Override
    public String toString() {
        return super.toString()+
                "uterus='" + uterus + '\'' +
                '}';
    }

    public void setUterus(String uterus) {
        this.uterus = uterus;
    }
}
