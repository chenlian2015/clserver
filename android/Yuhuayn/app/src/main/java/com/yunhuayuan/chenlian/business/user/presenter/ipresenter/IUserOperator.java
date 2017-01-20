package com.yunhuayuan.chenlian.business.user.presenter.ipresenter;

import com.yunhuayuan.chenlian.business.user.model.bean.LoginRequest;
import com.yunhuayuan.chenlian.business.user.view.viewlistener.ILogin;

/**
 * Created by chenlian on 16/8/27.
 */
public interface IUserOperator {
    void login(ILogin il, LoginRequest lr);
}
