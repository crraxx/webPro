package com.ff.jspDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ajax1", urlPatterns = "/ajax1")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (account.equals("admin")){
            out.print("账号已存在");//向异步请求做出响应
        }else{
            out.print("√");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (account.equals("admin")){
            out.print("账号已存在");
        }else{
            out.print("√");
        }
    }
}
