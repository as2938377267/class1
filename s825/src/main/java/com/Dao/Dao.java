package com.Dao;

import com.DBUtil.Dbutil;
import com.pojo.UserInfo;

import java.util.List;

public class Dao {

    public static int getcount() {
        String  sql = "select count(*) from userinfo";
        int n = Dbutil.uniqueQuery(sql);
        return n;

    }

    public List<UserInfo> fenye(int page, int size) {
        String sql = "select username,password from userinfo limit ?,?";
        List<UserInfo> list = Dbutil.query(sql, UserInfo.class,(page-1)*size, size);
        return list;
    }
}
