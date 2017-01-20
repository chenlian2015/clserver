package com.yuhuayuan.core.domain;

import java.math.BigDecimal;

public class goods {
    private Integer id;

    private String goodsid;

    private String goodsname;

    private BigDecimal goodsorignprice;

    private BigDecimal goodsellprice;

    private String goodsdescribe;

    private String goodsimageurl;

    private Integer goodsstatus;

    private String discount;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public BigDecimal getGoodsorignprice() {
        return goodsorignprice;
    }

    public void setGoodsorignprice(BigDecimal goodsorignprice) {
        this.goodsorignprice = goodsorignprice;
    }

    public BigDecimal getGoodsellprice() {
        return goodsellprice;
    }

    public void setGoodsellprice(BigDecimal goodsellprice) {
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

    public Integer getGoodsstatus() {
        return goodsstatus;
    }

    public void setGoodsstatus(Integer goodsstatus) {
        this.goodsstatus = goodsstatus;
    }

	private String getDiscount() {
		float discountTmp  = (this.goodsellprice.floatValue()/this.goodsorignprice.floatValue());
		float discountTwoPoints = ((float)Math.round(discountTmp*100))/100; 
		return ""+discountTwoPoints;
	}

	private void setDiscount(String discount) {
		this.discount = discount;
	}
}