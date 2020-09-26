package com.learn.ssm.chapter4.pojo;

import java.io.InputStream;

public class TestFile {
    long id ;
//    byte[] content;
    InputStream content;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }
}
