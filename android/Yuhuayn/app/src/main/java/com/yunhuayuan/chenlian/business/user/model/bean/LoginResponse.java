package com.yunhuayuan.chenlian.business.user.model.bean;

/**
 * Created by chenlian on 16/7/3.
 */
public class LoginResponse {

    private User o;
    private String status;
    private String msg;

    public LoginResponse()
    {
    }

    public LoginResponse(User o, String status)
    {
        this.o = o;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getO() {
        return o;
    }

    public void setO(User o) {
        this.o = o;
    }
}
