package com.yunhuayuan.chenlian.business.user.presenter;

import com.yunhuayuan.chenlian.business.user.model.bean.LoginRequest;
import com.yunhuayuan.chenlian.business.user.model.bean.User;
import com.yunhuayuan.chenlian.business.user.model.nethelper.UserNetHelper;
import com.yunhuayuan.chenlian.business.user.presenter.ipresenter.IUserOperator;
import com.yunhuayuan.chenlian.business.user.view.viewlistener.ILogin;
import com.yunhuayuan.chenlian.utils.localstore.SharedUtils;

/**
 * Created by chenlian on 16/8/27.
 */
public class UserOperator implements IUserOperator {

    public static interface ILoginOp
    {
        public void loginResult(User u, String code, String msg);
    }

    @Override
    public void login(final ILogin il, LoginRequest lr) {
        UserNetHelper.post2PhoneAndWeixinRegist(new ILoginOp(){
            @Override
            public void loginResult(User u, String code, String msg) {
                if(null != u && null != u.getCurrentLoginUUID()) {
                    SharedUtils.put(SharedUtils.LOGIN_T, u.getCurrentLoginUUID());
                }
                il.loginResult(code, msg);
            }
        }, lr);
    }
}
