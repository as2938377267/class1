package com.dao;

import com.pojo.Collection;
import com.pojo.User;
import com.util.Dbutil;

import java.util.List;

public class UserDao  {


    public List<Collection> shoucang(String username, String goodsid) {
        String sql = "select goodsid,username from collection where goodsid = ? and username = ?";
        List<Collection> list = Dbutil.query(sql,Collection.class,username,goodsid);
        return list;
    }

    public void addshoucang(String username, String goodsid) {
        String sql = "insert into collection(username,goodsid) values(?,?)";
        Dbutil.zsg(sql,username,goodsid);
    }

    public User login(String username, String password) {
        String sql = "select username,password from userinfo where username = ? and password = ?";
        List<User> list = Dbutil.query(sql,User.class,username,password);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
