package com.oods.thelast;

import android.app.RemoteAction;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public Void onMessageReceived(RemoteAction remoteAction) {
       generateNotification ();
    }
}
