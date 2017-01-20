package com.yunhuayuan.chenlian.business.user.model.bean;

import java.math.BigDecimal;

public class User {
    private Integer id;

    private String name;

    private String phone;

    private String headpic;

    private BigDecimal balance;

    private String currentLoginUUID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentLoginUUID() {
        return currentLoginUUID;
    }

    public void setCurrentLoginUUID(String currentLoginUUID) {
        this.currentLoginUUID = currentLoginUUID;
    }
}