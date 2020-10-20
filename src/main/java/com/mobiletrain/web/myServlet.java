package com.mobiletrain.web;

import com.mobiletrain.dao.DAO;
import com.mobiletrain.domain.User;
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
import java.util.List;

@WebServlet("/my")
public class myServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//1.接收
//2.处理
//3.响应
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
