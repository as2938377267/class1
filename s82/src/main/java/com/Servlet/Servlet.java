package com.Servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet implements javax.servlet.Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<form method='post' action=''>");
        pw.println("用户名：<input type='text' name='password'><br>");
        pw.println("密码：<input type='password' name='password'><br>");
        pw.println("<input type='submit' name='登录'><br>");
        pw.println("</form>");
        pw.println("</html>");




    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}






