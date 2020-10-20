package com.mobiletrain.dao;

import com.mobiletrain.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class hello {
    public static void main(String[] args) {
        // 1. 读取mybatis-config配置文件
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 获取SqlSessionFactory
        // 工厂，生产一个SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 获取SqlSession
        // openSession参数: true: 自动提交事务; false: 手动提交事务
        // 使用无参方法的话，默认为true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 4. 获取一个Mapper
        DAO mapper = sqlSession.getMapper(DAO.class);

        List<User> users = mapper.queryAll();
        User user = mapper.queryById(7);
        User user1 = mapper.queryById(10);

        // 如果使用jdbc-template的话，query会返回多条，要对这个集合做长度校验
        // 如果使用jdbc-template的话，queryFoObject会可能抛异常

        System.out.println(users);
        System.out.println(user);
        System.out.println(user1);

    }
}
