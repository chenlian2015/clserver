package yuhuayuan.technologyexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import yuhuayuan.technologyexample.hunxiao.bean.MyBean;

class MyThread extends HandlerThread
{
    public  volatile int nCounter = 0;
    public static android.os.Handler messageQueue;

    public MyThread(String name) {
        super(name);
    }


    public synchronized void ready() {
        messageQueue = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.e("child thread handler:", "msg.what:"+msg.what);
                return false;
            }
        });

    }

    @Override
    public void run() {
        super.run();
        while (true)
        {
            Log.e("child thread run", ""+currentThread().getId());
            try {
                Thread.sleep(300);
            }catch (Exception e)
            {

            }
        }
    }


}

public class ThreadHandlerMainActivity extends AppCompatActivity {

    public static boolean isScreenLocked = false;
    public static boolean bCurrentActivityActive = false;
    public String TAG = "ThreadHandlerMainActivity";

    public static Handler mainHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            Log.e("main thread handler:", "msg.what:"+msg.what);
            MyThread.messageQueue.sendEmptyMessage(2);
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_tv_hunxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyThread  myThread = new MyThread("boy");
                myThread.start();
                myThread.ready();
                ThreadHandlerMainActivity.mainHandler.sendEmptyMessage((int)Thread.currentThread().getId());
            }
        });
    }

    private void screenLockReceiver()
    {
        BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {



                if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                    ThreadHandlerMainActivity.isScreenLocked = true;
                    Log.e(TAG, "isScreenLocked:"+isScreenLocked);
                }
                if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
                    ThreadHandlerMainActivity.isScreenLocked = false;
                    Log.e(TAG, "isScreenLocked:"+isScreenLocked);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);

        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        bCurrentActivityActive = true;
        Log.e(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        bCurrentActivityActive = false;
        Log.e(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    public void testHunXiao(TextView tv)
    {
        MyBean my = new MyBean();
        my.setName("陈炼");

        my.id = 1;
        my.setBaseInfo(new MyBean.School());
        my.getBaseInfo().age = 1;
        my.getBaseInfo().schoolName = "pianyan";

        try {


            String str = new Gson().toJson(my)+"xxx";

            Toast.makeText(this, str+my.getAnnotion(), Toast.LENGTH_SHORT).show();
            tv.setText(str);

        }catch (Exception e)
        {
            Log.e(TAG, e.toString());
        }
    }
}
