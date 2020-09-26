package com.learn.ssm.chapter4.mapper;

import com.learn.ssm.chapter4.pojo.TestFile;

public interface TestFileMapper {
    public TestFile getFileById(Long id);
    public int insertFile(TestFile file);

}
