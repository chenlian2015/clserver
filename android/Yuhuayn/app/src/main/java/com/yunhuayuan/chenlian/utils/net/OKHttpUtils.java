package com.yunhuayuan.chenlian.utils.net;

/**
 * Created by chenlian on 16/7/3.
 */

import android.os.Handler;
import android.os.Looper;

import com.yunhuayuan.chenlian.utils.localstore.SharedUtils;
import com.yunhuayuan.chenlian.utils.log.DebugLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OKHttpUtils {
    private static final String TAG = "ehome" + OKHttpUtils.class.getSimpleName();

    private static OKHttpUtils mInstance;

    private OkHttpClient mOKHttpClient;

    private Handler mHandler;

    private static String _url;

    private int _method;

    private static Map<String, String> _headers;

    private RequestBody _requestBody;

    private static final int DEFAUTL_TIMEOUT = 15000;

    private static final int READ_TIMEOUT = 30000;

    private static final int WRITE_TIMEOUT = 30000;

    private OKHttpUtils() {
        mOKHttpClient = new OkHttpClient();

        mHandler = new Handler(Looper.getMainLooper());

        mOKHttpClient.newBuilder().retryOnConnectionFailure(false)
                .connectTimeout(DEFAUTL_TIMEOUT, TimeUnit.MILLISECONDS)
                //                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                //                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    public static OKHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OKHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OKHttpUtils();
                }
            }
        }

        if (_headers == null) {
            _headers = new HashMap<>();
        }

        String res = (String) SharedUtils.get(SharedUtils.LOGIN_T, "0");
        DebugLog.d(TAG, "okkhttp工具填充头信息，_t：" + res);
        if (!"0".equals(res)) {
            _headers.put("_t", res);
        } else {
            _headers.put("_t", "");
        }
        return mInstance;
    }

    public OKHttpUtils headers(String... headerPairs) {
        if (_headers == null) {
            _headers = new HashMap<>();
        }
        if (headerPairs.length < 2) {
            return mInstance;
        }
        for (int i = 0; i < headerPairs.length - 1; i += 2) {
            _headers.put(headerPairs[i], headerPairs[i + 1]);
        }
        return mInstance;
    }

    public OKHttpUtils method(int method) {
        _method = method;
        return mInstance;
    }

    public OKHttpUtils url(String url) {
        _url = url;
        return mInstance;
    }

    /**
     * 上传jsonMap
     *
     * @param kvPairs 参数
     * @return this
     */
    public OKHttpUtils postJsonMap(Object... kvPairs) {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < kvPairs.length - 1; i += 2) {
            try {
                jsonObject.put(kvPairs[i].toString(), kvPairs[i + 1]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        _requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), jsonObject.toString());
        return mInstance;
    }


    /**
     * 上传json字符串
     *
     * @param jsonStr json字符串
     * @return this
     */
    public OKHttpUtils postJsonString(String jsonStr) {
        _requestBody = RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), jsonStr.toString());
        return mInstance;
    }

    /**
     * 上传单文件
     *
     * @param file 文件
     * @return this
     */
    public OKHttpUtils postFile(File file) {
        _requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file);

        return mInstance;
    }


    /**
     * post提交
     *
     * @param responseCallBack 响应回调
     */
    public void postExecute(final ResponseCallBack responseCallBack) {
        try {

            Request request = new Request.Builder().headers(Headers.of(_headers)).post(_requestBody).url(_url).build();
            Call call = mOKHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sendFailResponse(call, e, responseCallBack);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String o = responseCallBack.parseNetworkResponse(response);
                        sendSuccessResponse(call, o, responseCallBack);
                    } catch (Exception e) {
                        DebugLog.i(TAG, e.toString());
                    }
                }
            });
        } catch (Exception e) {
            DebugLog.i(TAG, e.toString());
        }
    }

    /**
     * get提交
     *
     * @param responseCallBack 响应回调
     */
    public void getExecute(final ResponseCallBack responseCallBack) {
        try {
            Request request = new Request.Builder().headers(Headers.of(_headers)).url(_url).get().build();
            Call call = mOKHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sendFailResponse(call, e, responseCallBack);
                }

                @Override
                public void onResponse(Call call, Response response) {
                    try {
                        String o = responseCallBack.parseNetworkResponse(response);
                        sendSuccessResponse(call, o, responseCallBack);
                    } catch (Exception e) {
                        DebugLog.i(TAG, e.toString());
                    }
                }
            });
        } catch (Exception e) {
            DebugLog.i(TAG, e.toString());
        }
    }

    /**
     * 发送fail的响应
     *
     * @param call             call对象
     * @param e                IOException
     * @param responseCallBack 响应回调
     */
    public void sendFailResponse(final Call call, final IOException e, final ResponseCallBack responseCallBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                responseCallBack.onFail(call, e);
            }
        });
    }

    /**
     * 发送success的响应
     *
     * @param call             call对象
     * @param o                object类型
     * @param responseCallBack 回调
     */
    public void sendSuccessResponse(final Call call, final Object o, final ResponseCallBack responseCallBack) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                responseCallBack.onSuccess(call, o);
            }
        });

    }
}
