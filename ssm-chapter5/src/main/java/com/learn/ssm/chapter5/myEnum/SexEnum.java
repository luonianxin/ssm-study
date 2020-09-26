package com.learn.ssm.chapter5.myEnum;

public enum SexEnum {
    MALE(1,"男")
    ,FEMALE(0,"女");
    private int id ;
    private String name;

    SexEnum(int id,String name){
        this.id = id ;
        this.name = name ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *  提供一个共有方法供外部访问获取枚举类，通过自定义类型处理类 来处理查询出的结果集合
     * @param id
     * @return
     */
    public static SexEnum getSexEnumById(int id){
        for(SexEnum sexEnum: SexEnum.values()){
            if(id == sexEnum.getId()){
                return sexEnum;
            }
        }
        return null;
    }
}
