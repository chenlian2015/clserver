package yuhuayuan.technologyexample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by chenlian on 16/10/24.
 */
public class ShakeListener implements SensorEventListener {

    private static String TAG = "";
    private static final int SPEED_SHRESHOLD = 4000;//这个值越大需要越大的力气来摇晃手机
    private Context mContext;
    private float lastX;
    private float lastY;
    private float lastZ;
    private long lastUpdateTime;

    private static final int SHAKE_INTERVAL_TIME = 1000;

    private static boolean isCanSahke = true;

    private static final int UPTATE_INTERVAL_TIME = 90;
    private SensorManager sensorManager;
    private Sensor sensor;

    public void start() {
        try {
            sensorManager = (SensorManager) MyApplication.getInstance()
                    .getSystemService(Context.SENSOR_SERVICE);
            if (sensorManager != null) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            if (sensor != null && sensorManager != null) {
                sensorManager.registerListener(this, sensor,
                        SensorManager.SENSOR_DELAY_GAME);
            }
        }catch (Exception e)
        {
            Log.e(TAG, e.toString());
        }
    }

//    @TargetApi(21)
//    private static boolean isScreenOnAPI21() {
//        PowerManager pm = (PowerManager) MyApplication.getInstance().getSystemService(Context.POWER_SERVICE);
//        return pm.isInteractive();
//    }

    private static boolean isScreenOnAPI7() {
        PowerManager pm = (PowerManager) MyApplication.getInstance().getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }


    public static boolean isScreenOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return isScreenOnAPI7();
        } else {
            return isScreenOnAPI7();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isScreenOn()) {
            return;
        }

        long currentUpdateTime = System.currentTimeMillis();
        long timeInterval = currentUpdateTime - lastUpdateTime;
        if (timeInterval < UPTATE_INTERVAL_TIME)
            return;
        lastUpdateTime = currentUpdateTime;
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;
        lastX = x;
        lastY = y;
        lastZ = z;
        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                * deltaZ)
                / timeInterval * 10000;
        if (speed >= SPEED_SHRESHOLD && isCanSahke) {


            if (ThreadHandlerMainActivity.bCurrentActivityActive) {
                Log.e(TAG, "fore groudn");
            }
            else if(ThreadHandlerMainActivity.isScreenLocked)
            {
                Log.e(TAG, "back  ground");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
