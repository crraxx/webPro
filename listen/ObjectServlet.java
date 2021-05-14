package com.ff.listen;

import javax.naming.Context;
import javax.servlet.*;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/*
监听器:监听servletContext.HttpSession和ServletRequest等域对象的创建和销毁事件
servletContext:生命周期开始服务器启动,结束于服务器关闭
HttpSession:开始于会话创建,结束于1.服务器关闭  2.30分钟未操作  3.强制销毁
 ServletRequest:开始于请求,结束于服务器向客户端做出响应
 */
public class ObjectServlet implements ServletContextListener, HttpSessionListener, ServletRequestListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("path",context.getContextPath());
    }
}
