package com.ff.jspDemo;

import com.ff.bean.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ajax2", urlPatterns = "/ajax2")
public class AjaxServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //省略数据库查询
        User user1 = new User();
        user1.setName(user);
        user1.setAge(21);
        User user2 = new User();
        user2.setName(user);
        user2.setAge(22);
        User user3 = new User();
        user3.setName(user);
        user3.setAge(23);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Gson gson = new Gson();
        String s = gson.toJson(list);
        System.out.println(s);
        out.print(s);

/*
        //先把java对象转为json格式的字符串
        //String s = "{name:"+user1.getName()+",age:"+user1.getAge()+"}";
        Gson gson = new Gson();
        String s = gson.toJson(user1);
        System.out.println(s);
        out.print(s);*/
    }
}
