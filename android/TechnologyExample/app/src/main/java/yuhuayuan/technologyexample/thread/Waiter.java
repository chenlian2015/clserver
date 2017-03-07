package yuhuayuan.technologyexample.thread;

/**
 * Created by cl on 2017/3/7.
 */

public class Waiter implements Runnable{

    private Message msg;

    public Waiter(Message m){
        this.msg=m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{

                ThreadMainActivity.sendMessage(name+" waiting to get notified at time:"+System.currentTimeMillis());
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            ThreadMainActivity.sendMessage(name+" waiter thread got notified at time:"+System.currentTimeMillis());
        }
    }

}