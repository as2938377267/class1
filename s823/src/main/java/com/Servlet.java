package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/user.do")
public class Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("utf-8");
        String p = request.getParameter("p");
        if ("login".equals(p)){
            doLogin(request,response);
    }
        if ("regist".equals(p)){
            doRegist(request,response);
        }


    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection connection =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s82","root","12369874");
            String sql = "insert into userinfo(username,password)value(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,username);
            ps.setObject(2,password);
            int n = ps.executeUpdate();
            if (n>0) {
                response.sendRedirect("main.jsp");
            } else {
                response.sendRedirect("regist.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

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

                HttpSession session = request.getSession();
                session .setAttribute("username",username);

                request.getRequestDispatcher("main.jsp").forward(request,response);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



