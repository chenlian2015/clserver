package com.yunhuayuan.chenlian.business.common;

/**
 * Created by chenlian on 16/7/3.
 */
public class GsonResult<T> {
    private String o;
    private String status;
    private String msg;

    public GsonResult()
    {
    }

    public GsonResult(String o, String status)
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

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }
}