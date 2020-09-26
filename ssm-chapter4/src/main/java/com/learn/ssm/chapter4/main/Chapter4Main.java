package com.learn.ssm.chapter4.main;

import com.learn.ssm.chapter4.mapper.RoleMapper;
import com.learn.ssm.chapter4.mapper.TestFileMapper;
import com.learn.ssm.chapter4.mapper.UserMapper;
import com.learn.ssm.chapter4.pojo.Role;
import com.learn.ssm.chapter4.pojo.TestFile;
import com.learn.ssm.chapter4.pojo.User;
import com.learn.ssm.chapter4.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.*;

public class Chapter4Main {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        User user = mapper.selectUserById(2L);
//        User user2 = mapper.selectUserById(1L);
//        System.out.println(user.getSex().getName());
//        TestFileMapper fileMapper = sqlSession.getMapper(TestFileMapper.class);
//
//        File file = new File("/home/lnx/examples.desktop");
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
//        byte []bytes = new byte[1024] ;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        int l=0;
//        while((l=bufferedInputStream.read(bytes))!=-1){
//            bos.write(bytes,0,l);
//        }
//        byte [] b = bos.toByteArray();
//        TestFile f2 = new TestFile();
//        f2.setContent(b);
//        fileMapper.insertFile(f2);
        //sqlSession.commit();
//        TestFile f = fileMapper.getFileById(2L);
//        file = new File("test2.desktop");
//        FileOutputStream fous = new FileOutputStream(file);
//        InputStream b = f.getContent();
//        byte [] datas = new byte[1024];
//        int l = 0;
//        while((l= b.read(datas))!= -1){
//            fous.write(datas,0,l);
//        }
//        System.out.println(datas.length);
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        Role role = roleMapper.getRole(7L);
        System.out.println(role.getRoleName());
    }
}
