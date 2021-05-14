package com.ff.jspDemo;

import com.ff.bean.Admin;
import com.ff.bean.Course;
import com.ff.bean.Grade;
import com.ff.bean.Student;
import com.ff.dao.CourseDao;
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
@WebServlet(name = "course", urlPatterns = "/course")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("list")) {
            findCourseList(req, resp);
        }
        if (mark.equals("toadd")) {
            toAdd(req, resp);
        }
        if (mark.equals("del")) {
            delCourse(req, resp);
        }
    }

    //接收表单提交的请求调用save方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("save")) {
            saveCourse(req, resp);
        }
    }

    //获取课程信息，连接数据库
    private void findCourseList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            CourseDao courseDao = new CourseDao();
            List<Course> courseList = courseDao.findCourseList();
            req.setAttribute("courseList", courseList);
            req.getRequestDispatcher("Course/list.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }


    //保存课程信息
    private void saveCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收保存学生信息
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            //向数据库中传输保存
            CourseDao courseDao = new CourseDao();
            courseDao.saveCourse(id, name);
            findCourseList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //添加课程信息
    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            req.getRequestDispatcher("Course/add.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //删除课程
    private void delCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收删除学生的id
            String id = req.getParameter("id");
            CourseDao courseDao = new CourseDao();
            courseDao.deleteCourse(id);
            //响应
            findCourseList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }
}
