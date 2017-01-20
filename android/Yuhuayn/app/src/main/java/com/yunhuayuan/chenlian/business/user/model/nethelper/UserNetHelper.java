package com.yunhuayuan.chenlian.business.user.model.nethelper;

import com.google.gson.Gson;
import com.yunhuayuan.chenlian.business.common.net.NetUrlConstant;
import com.yunhuayuan.chenlian.business.common.net.ServerErrorCode;
import com.yunhuayuan.chenlian.business.user.model.bean.LoginRequest;
import com.yunhuayuan.chenlian.business.user.model.bean.LoginResponse;
import com.yunhuayuan.chenlian.business.user.presenter.UserOperator;
import com.yunhuayuan.chenlian.utils.net.OKHttpUtils;
import com.yunhuayuan.chenlian.utils.net.ResponseCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chenlian on 16/7/3.
 */
public class UserNetHelper {

    public static void post2PhoneAndWeixinRegist(final UserOperator.ILoginOp il, LoginRequest registRequest) {

        String url = NetUrlConstant.login;
        OKHttpUtils.getInstance().url(url).postJsonString(new Gson().toJson(registRequest)).postExecute(new ResponseCallBack<String>() {
            @Override
            public void onSuccess(Call call, String response) {

                String res = response;
                if(null == response)
                {
                    il.loginResult(null, ServerErrorCode.FAILED.getCode(), "网络错误!");
                }
                else
                {
                    LoginResponse loginRes = null;
                    try {
                        loginRes = new Gson().fromJson(res, LoginResponse.class);
                    }catch(Exception e)
                    {
                        il.loginResult(null, ServerErrorCode.FAILED.getCode(), "网络错误!");
                        return;
                    }

                    il.loginResult(loginRes.getO(), loginRes.getStatus(), loginRes.getMsg());
                }
            }

            @Override
            public void onFail(Call call, IOException e) {

            }

            @Override
            public String parseNetworkResponse(Response response) {
                try {
                    String string = response.body().string();
                    return string;
                } catch (Exception e) {
                }
                return "";
            }

        });


    }
}

