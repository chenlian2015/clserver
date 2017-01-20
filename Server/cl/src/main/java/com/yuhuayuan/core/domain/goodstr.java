package com.yuhuayuan.core.domain;


public class goodstr {
    private String id;

    private String goodsid;

    private String goodsname;

    private String goodsorignprice;

    private String goodsellprice;

    private String goodsdescribe;

    private String goodsimageurl;

    private String goodsstatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public String getGoodsorignprice() {
        return goodsorignprice;
    }

    public void setGoodsorignprice(String goodsorignprice) {
        this.goodsorignprice = goodsorignprice;
    }

    public String getGoodsellprice() {
        return goodsellprice;
    }

    public void setGoodsellprice(String goodsellprice) {
        this.goodsellprice = goodsellprice;
    }

    public String getGoodsdescribe() {
        return goodsdescribe;
    }

    public void setGoodsdescribe(String goodsdescribe) {
        this.goodsdescribe = goodsdescribe == null ? null : goodsdescribe.trim();
    }

    public String getGoodsimageurl() {
        return goodsimageurl;
    }

    public void setGoodsimageurl(String goodsimageurl) {
        this.goodsimageurl = goodsimageurl == null ? null : goodsimageurl.trim();
    }

    public String getGoodsstatus() {
        return goodsstatus;
    }

    public void setGoodsstatus(String goodsstatus) {
        this.goodsstatus = goodsstatus;
    }
}