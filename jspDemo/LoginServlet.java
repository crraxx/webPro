package com.ff.jspDemo;

import com.ff.bean.Admin;
import com.ff.dao.StudentDao;
import com.ff.util.JDBCUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ff.dao.LoginDao.checkAdmin;

@WebServlet(name = "login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    /*退出*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("login.jsp");
    }

    /*
     登录处理
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String ck = req.getParameter("ck");//是否记住账号密码的标记 ck    null
        try {
            Admin admin = checkAdmin(account,password);
            if (admin != null) {
                if (ck != null) {
                    //创建Cookie对象,封装数据, tomcat9之后可以直接存储中文
                    Cookie acccok = new Cookie("acccok", account);
                    Cookie pwdcok = new Cookie("pwdcok", password);
                    acccok.setMaxAge(3600 * 24 * 7);
                    pwdcok.setMaxAge(3600 * 24 * 7);
                    resp.addCookie(acccok);
                    resp.addCookie(pwdcok);
                }
                //在servlet中获得session
                HttpSession session = req.getSession();//从请求对象中获得与之对应的session对象
                session.setAttribute("admin",admin);
                resp.sendRedirect("success.jsp");
                String ip = req.getRemoteAddr();
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String login_time = dateFormat.format(date);
                StudentDao.inserts(account, ip, login_time);
            } else {
                req.setAttribute("msg", "账号或密码错误");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("500.jsp");//只跳转页面不进行数据传输
        }
    }
}
