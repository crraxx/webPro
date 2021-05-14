package com.ff.jspDemo;

import com.ff.bean.Course;
import com.ff.bean.Grade;
import com.ff.dao.CourseDao;
import com.ff.dao.GradeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
学生信息管理层
 */
@WebServlet(name = "grade", urlPatterns = "/grade")
public class GradeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("list")) {
            findGradeList(req, resp);
        }
        if (mark.equals("toadd")) {
            toAdd(req, resp);
        }
        if (mark.equals("del")) {
            delGrade(req, resp);
        }
    }

    //接收表单提交的请求调用save方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("save")) {
            saveGrade(req, resp);
        }
    }

    //获取年级信息，连接数据库
    private void findGradeList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            GradeDao gradeDao = new GradeDao();
            List<Grade> gradeList = gradeDao.findGradeList();
            req.setAttribute("gradeList", gradeList);
            req.getRequestDispatcher("Grade/list.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }


    //保存年级信息
    private void saveGrade(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收保存学生信息
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            //向数据库中传输保存
            GradeDao gradeDaoDao = new GradeDao();
            gradeDaoDao.saveGrade(id, name);
            findGradeList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //添加年级信息
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("Grade/add.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //删除年级
    private void delGrade(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收删除学生的id
            String id = req.getParameter("id");
            GradeDao gradeDaoDao = new GradeDao();
            gradeDaoDao.deleteGrade(id);
            //响应
            findGradeList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }
}
