package com.dao;

import com.pojo.Goods;
import com.pojo.Pic;
import com.util.Dbutil;

import java.util.List;

public class GoodDao {
    public int getCount() {
        String sql = "select count(*) from goods";
        int n = Dbutil.uniqueQuery(sql);
        return n;
    }

    public List<Goods> findall(int page, int size) {
        String sql = "select goodsid , goodsname , goodscount , goodsprice ,goodspic from goods limit ?, ?";
        List<Goods> list = Dbutil.query(sql, Goods.class, (page - 1) * size, size);
        return list;
    }

    public Goods cha(String goodsid) {
        String sql = "select goodsid , goodsname , goodscount , goodsprice ,goodspic from goods where goodsid = ?";
        List<Goods> list = Dbutil.query(sql,Goods.class,goodsid);

        String sql2 = "select pid,pname from picture where gid=?";
        List<Pic> pic = Dbutil.query(sql2,Pic.class,goodsid);

        if (list.size()==0){
            return null;
        }
        list.get(0).getSet().addAll(pic);

        return list.get(0);

    }


}
