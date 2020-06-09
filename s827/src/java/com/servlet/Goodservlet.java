package com.servlet;

import com.dao.GoodDao;
import com.pojo.Goods;
import com.sun.net.httpserver.HttpsServer;
import com.util.Dbutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/goods.do")
public class Goodservlet extends HttpServlet {

    private GoodDao goodDao = new GoodDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String p = request.getParameter("p");
        if("fenye".equals(p)){
            doFenye(request , response);
        }
        if ("findbyid".equals(p)){
            doCha(request,response);
        }
        if ("addCar".equals(p)){
            doAddcar(request,response);
        }
        if ("deletcar".equals(p)){
            doDeletecar(request,response);
        }
        if ("addnum".equals(p)){
            doAddnum(request,response);
        }
        if ("jian".equals(p)){
            doJian(request,response);
        }

    }

    private void doJian(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid = request.getParameter("goodsid");
        Goods goods =goodDao.cha(goodsid);

        Map map = (Map) request.getSession().getAttribute("map");
        Integer num = (Integer) map.get(goods);

        map.put(goods,num-1);
        if (num==1){
            map.remove(goods);
        }
        response.sendRedirect("car.jsp");


    }

    private void doAddnum(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid = request.getParameter("goodsid");
        Goods goods = goodDao.cha(goodsid);

        Map map = (Map) request.getSession().getAttribute("map");
        Integer num = (Integer) map.get(goods);
        map.put(goods,num+1);
        response.sendRedirect("car.jsp");


    }

    private void doDeletecar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String  goodsid = request.getParameter("goodsid");
        Goods goods = goodDao.cha(goodsid);

        HttpSession session = request.getSession();
        Map map = (Map) session.getAttribute("map");

        map.remove(goods);
        response.sendRedirect("car.jsp");


    }

    private void doAddcar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String goodsid = request.getParameter("goodsid");
        int num = Integer.parseInt(request.getParameter("num"));


        Goods goods = goodDao.cha(goodsid);

        HttpSession session = request.getSession();
        Map<Goods, Integer> map = (Map) session.getAttribute("map");

        if (map ==null){
            map= new HashMap<Goods, Integer>();
        }
        Integer n = map.get(goods);
        if (n==null){
            map.put(goods,num);
        }else {
            map.put(goods,n+num);
        }
        session.setAttribute("map",map);
        response.sendRedirect("car.jsp");


    }

    private void doCha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsid = request.getParameter("goodsid");
        Goods goods = goodDao.cha(goodsid);
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("showDe.jsp").forward(request,response);


    }

    private void doFenye(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageString = request.getParameter("page");
        String sizeString = request.getParameter("size");

        int page = 1;
        int size = 1;

        if(pageString !=null && pageString.trim().length()>0){
            page = Integer.parseInt(pageString);
        }

        if (sizeString !=null && sizeString.trim().length()>0){
            size = Integer.parseInt(sizeString);
        }

        if (page<1){
            page = 1;
        }

        //得到总条数
        int count = goodDao.getCount();
        int pagecount = count % size == 0 ? count / size : count / size + 1;
        if (page>pagecount){
            page = pagecount;
        }

        List<Goods> list = goodDao.findall(page, size);
        System.out.println(list.size());

        Map map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        map.put("count",count);
        map.put("pagecount",pagecount);
        map.put("list",list);

        request.setAttribute("map",map);
        request.getRequestDispatcher("show.jsp").forward(request,response);


    }
}
