package com.Login;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class LoginAction implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s82","root","12369874");
            String sql = "select username , password from userinfo where username = ? and password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            ResultSet rs = ps.executeQuery();
            PrintWriter pw = response.getWriter();
            if (rs.next()) {
                pw.println("yes");
            } else {
                pw.println("no");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
