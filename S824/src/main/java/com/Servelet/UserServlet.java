package com.Servelet;

import com.Dao.Dao;
import com.pojo.Userinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/user.do")
public class UserServlet extends HttpServlet {

    private Dao dao = new Dao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String p = request.getParameter("p");

        if("login".equals(p)){
            doLogin(request , response);
        }
        if ("regist".equals(p)){
            doRegist(request,response);
        }
        if ("findall".equals(p)){
            doFindall(request,response);
        }
        if ("del".equals(p)){
            doDel(request,response);

        }

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int n = dao.del(username);
        doFindall(request,response);

    }

    private void doFindall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Userinfo> list =Dao.findall();
        request.setAttribute("list",list);
        request.getRequestDispatcher("show.jsp").forward(request,response);

    }

    private void doRegist(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        int n = Dao.Regist(username , password);

         if (n>0){
             response.getWriter().println("<script>alert('注册成功');location='login.jsp';</script>");
         } else{
             response.getWriter().println("<script>alert('注册失败');location='regist.jsp';</script>");
         }


    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Userinfo userInfo = dao.Login(username , password);
        if(userInfo!=null){
            request.getSession().setAttribute("userinfo" , userInfo);
            request.getRequestDispatcher("main.jsp").forward(request,response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }
}
