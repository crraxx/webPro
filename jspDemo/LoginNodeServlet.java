package com.ff.jspDemo;

import com.ff.bean.*;
import com.ff.dao.LoginNodeDao;
import com.ff.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/*
学生信息管理层
 */
@WebServlet(name = "loginNode", urlPatterns = "/loginNode")
public class LoginNodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("list")){
            findLoginList(req,resp);
        }
    }

    //接收表单提交的请求调用save方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("save")) {
        }
    }

    /*查询所有的操作信息 */
    private void findLoginList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            LoginNodeDao loginNodeDao = new LoginNodeDao();
            List<Login_infor> logininfors = loginNodeDao.findLoginList();
            req.setAttribute("logininfors", logininfors);
            req.getRequestDispatcher("LoginNode/login_infor.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }
}
