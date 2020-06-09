package com.Login;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet implements Servlet {

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
        pw.println("<form method='post' action='loginAction.do'>");
        pw.println("username:<input type='text' name='username'><br>");
        pw.println("password:<input type='password' name='password'><br>");
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
