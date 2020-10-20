package com.qianfeng.web;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/com.mobiletrain.dao.hello")
public class HelloServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.接收
//2.处理
//3.响应
        // 1. 读取mybatis-config配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 获取SqlSessionFactory
        // 工厂，生产一个SqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 3. 获取SqlSession
        // openSession参数: true: 自动提交事务; false: 手动提交事务
        // 使用无参方法的话，默认为true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        Object o = sqlSession.selectOne("SELECT * FROM tb_user WHERE id=7");

        System.out.println(o);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
