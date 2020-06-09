package Dao;

import userinfo.Userinfo;
import util.Dbutil;

import java.util.List;

public class Dao {

    public static int getcount() {
        String sql="select count(*) from userinfo";
        int n = Dbutil.uniqueQuery(sql);
        return n;
    }

    public static List fenye(int page, int size) {
        String sql = "select username,password from userinfo limit ?,?";
        List<Userinfo> list = Dbutil.query(sql,Userinfo.class,(page-1)*size,size);
        return  list;
    }
}
