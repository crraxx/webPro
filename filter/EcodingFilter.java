package com.ff.filter;

import javax.servlet.*;
import java.io.IOException;

public class EcodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");//执行统一的编码
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
