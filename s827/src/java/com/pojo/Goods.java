package com.pojo;

import java.util.HashSet;
import java.util.Set;

public class Goods {
    private int goodsid;
    private String goodsname ;
    private String goodscount;
    private int goodsprice;
    private String goodspic;
    private Set<Pic> set = new HashSet<Pic>();


    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(String goodscount) {
        this.goodscount = goodscount;
    }

    public int getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(int goodsprice) {
        this.goodsprice = goodsprice;
    }

    public String getGoodspic() {
        return goodspic;
    }

    public void setGoodspic(String goodspic) {
        this.goodspic = goodspic;
    }


    public Set<Pic> getSet() {
        return set;
    }

    public void setSet(Set<Pic> set) {
        this.set = set;
    }

    @Override
    public int hashCode() {
        return  goodsid;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Goods){
            if(((Goods)obj).getGoodsid()==this.goodsid){
                return true ;
            }
        }
        return false ;
    }
}
