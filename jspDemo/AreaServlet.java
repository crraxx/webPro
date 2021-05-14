package com.ff.jspDemo;

import com.ff.bean.Area;
import com.ff.dao.AreaDao;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "area",urlPatterns = "/area")
public class AreaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        AreaDao areaDao = new AreaDao();
        try {
            List<Area> list = areaDao.findArea(pid);
            Gson gson = new Gson();
            out.print(gson.toJson(list));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
