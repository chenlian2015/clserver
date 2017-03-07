package yuhuayuan.technologyexample.thread;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import yuhuayuan.technologyexample.R;

public class ThreadMainActivity extends AppCompatActivity {

    private static final String TAG = "ThreadMainActivity";
    public Long nTick = new Long(100);

    public static String LOG_STRING = "str";
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            String str = msg.getData().getString(LOG_STRING);
            Log.e(TAG, str);
            super.handleMessage(msg);
        }
    };

    public static void sendMessage(String log)
    {
        Bundle bundle = new Bundle();
        bundle.putString(ThreadMainActivity.LOG_STRING, log);
        android.os.Message message = new android.os.Message();
        message.setData(bundle);
        ThreadMainActivity.handler.sendMessage(message);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_main);
        if(false) {//第一个测试例子
            Message msg = new Message("process it");
            Log.e(TAG, "MAIN THREAD START");
            Waiter waiter = new Waiter(msg);
            new Thread(waiter, "waiter").start();

            Waiter waiter1 = new Waiter(msg);
            new Thread(waiter1, "waiter1").start();

            Notifier notifier = new Notifier(msg);
            new Thread(notifier, "notifier").start();
            Log.e("All the started", "");
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        ThreadMainActivity.sendMessage("I am waiting for you"+nTick);
                        synchronized (nTick) {

                            nTick.wait();
                        }

                        ThreadMainActivity.sendMessage("I meet you");
                    } catch (InterruptedException e) {

                        ThreadMainActivity.sendMessage("excepton" + e.toString());
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {


                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    //nTick--;
                    //nTick++;

                    ThreadMainActivity.sendMessage("I notify you when " + nTick);
                    synchronized (nTick) {
                        nTick.notify();
                    }

                }
            }).start();
        }
    }
}
