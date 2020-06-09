package com.servlet;

import com.dao.UserDao;
import com.pojo.Collection;
import com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String  p = request.getParameter("p");
        if ("shoucang".equals(p)){
            doShoucang(request,response);
        }
        if ("login".equals(p)){
            doLogin(request,response);
        }


    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.login(username,password);
        if (user!=null){
            request.getSession().setAttribute("user" ,user);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }else {
            response.sendRedirect("login.jsp");
        }


    }

    private void doShoucang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //判断用户有没有登录
        User user = (User) request.getSession().getAttribute("user");

        if (user==null){
            response.getWriter().println("<script>alert('请先登录');location='login.jsp'</script>");
            return;
        }

        String goodsid = request.getParameter("goodsid");
        String username = user.getUsername();

        List<Collection> list = userDao.shoucang(username,goodsid);
        if (list.size()>0){
            response.getWriter().println("<script>alert('已经收藏');location='goods.do?p=findbyid&goodsid="+goodsid+"';</script>");
            return;
        }
        userDao.addshoucang(username,goodsid);

        response.getWriter().println("<script>alert('收藏成功');location='goods.do?p=findbyid&goodsid="+goodsid+"';</script>");


    }
}
