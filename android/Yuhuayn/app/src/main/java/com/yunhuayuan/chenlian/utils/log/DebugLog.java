package com.yunhuayuan.chenlian.utils.log;

/**
 * Created by chenlian on 16/7/3.
 */

import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 调试日志
 *
 * @author 792793182@qq.com 2015-06-11
 */
public final class DebugLog {

    /**
     * 调试日志的开关，一般Debug版本中打开，便于开发人员观察日志，Release版本中关闭
     */
    public static final boolean ENABLED = true;

    /**
     * 崩溃日志
     */
    private static final String FILE_UNCAUGHT_EXCEPITON_LOG = "UncaughtException.log";

    /**
     * 网络异常日志
     */
    public static final String FILE_HTTP_EXCEPTION_LOG = "HttpException.log";

    // private static final int MAX_FILE_SIZE = 5 * 1024 * 1024; //日志最大5M

    /**
     * TAG的前缀，便于过滤
     */
    private static final String PREFIX = "GD_";

    private DebugLog() {
    }


    public static int v(String tag, String msg) {
        return ENABLED ? Log.v(PREFIX + tag, "" + msg) : 0;
    }

    public static int v(String tag, String msg, Throwable throwable) {
        return ENABLED ? Log.v(PREFIX + tag, msg, throwable) : 0;
    }

    public static int d(String tag, String msg) {
        //华为的这款手机只能打印information信息
        if ("GEM-703L".equals(Build.MODEL)
                || "H60-L11".equals(Build.MODEL)) {
            return i(tag, msg);
        }
        return ENABLED ? Log.d(PREFIX + tag, "" + msg) : 0;
    }

    public static int d(String tag, String msg, Throwable throwable) {
        return ENABLED ? Log.d(PREFIX + tag, msg, throwable) : 0;
    }

    public static int i(String tag, String msg) {
        return ENABLED ? Log.i(PREFIX + tag, "" + msg) : 0;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return ENABLED ? Log.i(PREFIX + tag, msg, tr) : 0;
    }

    public static int w(String tag, String msg) {
        return ENABLED ? Log.w(PREFIX + tag, "" + msg) : 0;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return ENABLED ? Log.w(PREFIX + tag, msg, tr) : 0;
    }

    public static int w(String tag, Throwable tr) {
        return ENABLED ? Log.w(PREFIX + tag, tr) : 0;
    }

    public static int e(String tag, String msg) {
        return ENABLED ? Log.e(PREFIX + tag, "" + msg) : 0;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return ENABLED ? Log.e(PREFIX + tag, msg, tr) : 0;
    }

    /**
     * 保存异常堆栈信息
     *
     * @param e
     * @return
     */
    public static String getExceptionTrace(Throwable e) {
        if (e != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            return stringWriter.toString();
        }
        return null;
    }


    /**
     * 获取当前时间
     *
     * @return
     */
    private static String getFormatDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINESE)
                .format(System.currentTimeMillis());
    }

}
