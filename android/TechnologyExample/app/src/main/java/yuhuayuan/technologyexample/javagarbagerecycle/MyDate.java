package yuhuayuan.technologyexample.javagarbagerecycle;

/**
 * Created by cl on 2017/3/7.
 */


import android.util.Log;

import java.util.Date;

public class MyDate extends Date {

    /** Creates a new instance of MyDate */
    public MyDate() {
    }
    // 覆盖finalize()方法
    protected void finalize() throws Throwable {
        super.finalize();
        Log.e("obj [Date: " + this.getTime() + "] is gc", "");
    }

    public String toString() {
        return "Date: " + this.getTime();
    }
}
