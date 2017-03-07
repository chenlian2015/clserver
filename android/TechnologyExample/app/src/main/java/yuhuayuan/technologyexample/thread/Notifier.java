package yuhuayuan.technologyexample.thread;

import android.util.Log;

/**
 * Created by cl on 2017/3/7.
 */

public class Notifier implements Runnable {

    private Message msg;

    public Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        Log.e(name+" started", "");
        try {
            Thread.sleep(1000);
            synchronized (msg) {

               ThreadMainActivity.sendMessage(name+" Notifier work done");

                //msg.notify();
                //msg.notify();
                 msg.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
