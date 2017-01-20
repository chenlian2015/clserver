package yuhuayuan.technologyexample;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;

/**
 * Created by chenlian on 16/10/19.
 */
public class MyApplication extends Application {
    Activity currentActivity = null;

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 未捕获异常处理器
     */
    private static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

        private Context context;

        public MyUncaughtExceptionHandler(Context context) {
            this.context = context;
        }

        @Override
        public void uncaughtException(Thread thread, final Throwable ex) {
            // 使用 Toast 来显示异常信息


            Process.killProcess(Process.myPid());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(
                getApplicationContext()));

        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                currentActivity = activity;
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
