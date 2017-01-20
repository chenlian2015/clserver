package yuhuayuan.technologyexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

public class MyService extends Service {

    Messenger messenger;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        messenger.getBinder();
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
