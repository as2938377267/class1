package com.Dao;

import com.Dbutil.Dbutil;
import com.pojo.Userinfo;


import java.util.List;

public class Dao {

    public static List<Userinfo> findall() {
        String sql = "select username , password from userinfo";
        List<Userinfo> list= Dbutil.query(sql,Userinfo.class);
        return list;

    }

    public Userinfo Login(String username, String password) {
        String sql="select username , password from userinfo where username = ? and password = ?";

        List<Userinfo> list = (List<Userinfo>) Dbutil.query(sql,Userinfo.class,username,password);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }


    public static int Regist(String username, String password) {
        String sql="insert into userinfo( username, password) value (?,?)";
        int n = Dbutil.zsg(sql , username , password);
        return  n;
    }

    public int del(String username) {
        String sql = "delete from userinfo where username = ?";
        int n=Dbutil.zsg(sql,username);
        return n;

    }
}
