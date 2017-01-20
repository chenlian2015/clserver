package yuhuayuan.technologyexample.systemexit.ansynctask;


import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import yuhuayuan.technologyexample.R;

public class SystemExitAnsyTaskActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_exit_ansy_task);
        BlankFragment blankFragment = BlankFragment.newInstance("hello", "world");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.test_root, blankFragment);
        fragmentTransaction.commit();
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            SharedPreferences sharedPreferences = SystemExitAnsyTaskActivity.this.getBaseContext().getSharedPreferences("test", MODE_PRIVATE);
            Log.e("", sharedPreferences.getString("hello", "world"));
            return false;
        }
    });

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



    @Override
    protected void onResume() {
        super.onResume();
       // mHandler.sendEmptyMessageDelayed(0, 5000);


    }
}
