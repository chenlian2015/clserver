package yuhuayuan.technologyexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import yuhuayuan.technologyexample.hunxiao.bean.MyBean;
import yuhuayuan.technologyexample.systemexit.ansynctask.SystemExitAnsyTaskActivity;


public class MainActivity extends AppCompatActivity {

    public static boolean isScreenLocked = false;
    public static boolean bCurrentActivityActive = false;
    public String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.id_tv_hunxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SystemExitAnsyTaskActivity.class));
            }
        });

    }

    private void screenLockReceiver()
    {
        BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {



                if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
                    MainActivity.isScreenLocked = true;
                    Log.e(TAG, "isScreenLocked:"+isScreenLocked);
                }
                if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
                    MainActivity.isScreenLocked = false;
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
