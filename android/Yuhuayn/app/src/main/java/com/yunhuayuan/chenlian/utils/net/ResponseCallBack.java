package com.yunhuayuan.chenlian.utils.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by chenlian on 16/7/3.
 */
public abstract class ResponseCallBack<T> {
    public abstract void onSuccess(Call call, T response);

    public abstract void onFail(Call call, IOException e);

    public abstract String parseNetworkResponse(Response response) throws Exception;

}