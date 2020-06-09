package com.Servlet;


import com.Dao.Dao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user.do")
public class Servlet extends HttpServlet {

    private Dao dao = new Dao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String p = request.getParameter("p");
        if ("fenye".equals(p)){
            doFenye(request,response);
        }
    }

    private void doFenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageString = request.getParameter("page");
        String sizeString = request.getParameter("size");

        int page = 1;
        int size = 4;

        if (pageString != null && pageString.trim().length()>0){
            page = Integer.parseInt(pageString);
        }
        if (sizeString !=null && sizeString.trim().length()>0){
            size = Integer.parseInt(sizeString);
        }
        if (page<1){
            page=1;
        }

        int count = Dao.getcount();
        int pagecount = count % size == 0 ? count/size :count/size+1;
        if (page>pagecount){
            page=pagecount;
        }
        List list =dao.fenye(page,size);

        Map map = new HashMap<>();
        map.put("page",page);
        map.put("count",count);
        map.put("pagecount",pagecount);
        map.put("size",size);
        map.put("list",list);
        request.setAttribute("map",map);
        request.getRequestDispatcher("show.jsp").forward(request,response);




    }


}
