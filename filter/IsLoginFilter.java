package com.ff.filter;

import com.ff.bean.Admin;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IsLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            response.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
