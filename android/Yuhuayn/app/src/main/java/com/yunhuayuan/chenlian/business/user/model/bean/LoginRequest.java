package com.yunhuayuan.chenlian.business.user.model.bean;

/**
 * Created by chenlian on 16/7/3.
 */
public class LoginRequest {
    private String phone;
    private String checkCode;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
