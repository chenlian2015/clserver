package com.yuhuayuan.core.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class BalanceLog {
    private Integer id;

    private Integer yuhuayuanid;

    private BigDecimal balancenum;

    private String manager;

    private String balancecode;

    private Date insertdate;

    private Integer balancetype;

    private Map<String,String> otherinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYuhuayuanid() {
        return yuhuayuanid;
    }

    public void setYuhuayuanid(Integer yuhuayuanid) {
        this.yuhuayuanid = yuhuayuanid;
    }

    public BigDecimal getBalancenum() {
        return balancenum;
    }

    public void setBalancenum(BigDecimal balancenum) {
        this.balancenum = balancenum;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getBalancecode() {
        return balancecode;
    }

    public void setBalancecode(String balancecode) {
        this.balancecode = balancecode == null ? null : balancecode.trim();
    }

    public Date getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }

    public Integer getBalancetype() {
        return balancetype;
    }

    public void setBalancetype(Integer balancetype) {
        this.balancetype = balancetype;
    }

    public Map<String,String> getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(Map<String,String> otherinfo) {
        this.otherinfo = otherinfo == null ? null : otherinfo;
    }
}