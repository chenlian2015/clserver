package yuhuayuan.technologyexample;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import static yuhuayuan.technologyexample.GustureActivity.DEBUG_TAG;

public class GustureActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "GustureActivity";

    GestureDetectorCompat gestureDetectorCompat;
    GestureDetectorCompat gestureDetectorCompatImageView;
    MyTouchEventListener myTouchEventListenerGustureActivity = new MyTouchEventListener("GustureActivity");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gusture);

        View myView = findViewById(R.id.my_view);
        final MyTouchEventListener myviewTouchEventListener = new MyTouchEventListener("myview");
        myView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetectorCompatImageView.onTouchEvent(event);
                return myviewTouchEventListener.onTouchEvent(event);
            }
        });
        gestureDetectorCompat = new GestureDetectorCompat(this, new GustureListenerImpl("GustureActivityGustureListener"));

        gestureDetectorCompatImageView = new GestureDetectorCompat(myView.getContext(), new GustureListenerImpl("ImageViewGustureListener"));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }
}


class GustureListenerImpl implements GestureDetector.OnGestureListener
{
    private String name;
    public GustureListenerImpl(String namePar)
    {
        this.name = namePar;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(DEBUG_TAG+name, "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(DEBUG_TAG+name, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(DEBUG_TAG+name, "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG+name, "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(DEBUG_TAG+name, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG+name, "onFling");
        return false;
    }
}

class MyTouchEventListener {
    private String name;

    public MyTouchEventListener(String namePar) {
        this.name = namePar;
    }

    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                Log.d(DEBUG_TAG+name, "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE):
                Log.d(DEBUG_TAG+name, "Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d(DEBUG_TAG+name, "Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL):
                Log.d(DEBUG_TAG+name, "Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE):
                Log.d(DEBUG_TAG+name, "Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default:
                return false;
        }

    }

}