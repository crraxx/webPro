package com.ff.jspDemo;

import com.ff.bean.*;
import com.ff.dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/*
学生信息管理层
 */
@WebServlet(name = "student", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("list")) {
            findStudentList(req, resp);
        }
        if (mark.equals("toadd")) {
            toAdd(req, resp);
        }
        if (mark.equals("del")){
            delStudent(req,resp);
        }
        if (mark.equals("updateStu")){
            updateStudent(req,resp);
        }
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String id = req.getParameter("id");
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.findStudentById(id);
            req.setAttribute("student", student);
            List<Course> courseList = studentDao.findCourseList();
            List<Grade> gradeList = studentDao.findGrade();
            req.setAttribute("courseList", courseList);
            req.setAttribute("gradeList", gradeList);
            req.getRequestDispatcher("Student/updateStu.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //接收表单提交的请求调用save方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mark = req.getParameter("mark");
        if (mark.equals("save")) {
            saveStudent(req, resp);
        }
        if (mark.equals("check")){
            String num = req.getParameter("num");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            StudentDao studentDao = new StudentDao();
            try {
                int res = studentDao.findNum(num);
                out.print(res);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                out.print(-1);
            }
        }

    }

    //保存学生信息
    private void saveStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收保存学生信息
            String id = req.getParameter("id");
            String num = req.getParameter("num");
            String name = req.getParameter("name");
            String sex = req.getParameter("sex");
            String birthday = req.getParameter("birthday");
            String mobile = req.getParameter("mobile");
            String address = req.getParameter("address");
            String gradeId = req.getParameter("gradeId");
            String[] course = req.getParameterValues("course");


            //操作人
            HttpSession session = req.getSession();
            Admin admin = (Admin) session.getAttribute("admin");


            //向数据库中传输保存
            StudentDao studentDao = new StudentDao();
            if (id == null){
                studentDao.saveStudent(num, name, sex, birthday, mobile, address, gradeId, course, admin.getId());
            }else{
                studentDao.updateStudent(id, name, sex, birthday, mobile, address, gradeId, course, admin.getId());
            }
            findStudentList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }


    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*  查询年级，课程  跳转到add.jsp  */
        try {
            StudentDao studentDao = new StudentDao();
            List<Course> courseList = studentDao.findCourseList();
            List<Grade> gradeList = studentDao.findGrade();
            req.setAttribute("courseList", courseList);
            req.setAttribute("gradeList", gradeList);
            req.getRequestDispatcher("Student/add.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    /*查询所有的学生信息 */
    private void findStudentList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            StudentDao studentDao = new StudentDao();
            List<Student> studentList = studentDao.findStudentList();
            req.setAttribute("studentList", studentList);
            req.getRequestDispatcher("Student/list.jsp").forward(req, resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }

    //删除学生
    private void delStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            //接收删除学生的id
            String id = req.getParameter("id");
            StudentDao studentDao = new StudentDao();
            studentDao.deleteStudent(id);
            //响应
            findStudentList(req,resp);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            resp.sendRedirect("500.jsp");
        }
    }
}
