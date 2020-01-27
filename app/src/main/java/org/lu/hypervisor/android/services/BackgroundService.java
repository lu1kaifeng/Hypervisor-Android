package org.lu.hypervisor.android.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class BackgroundService extends IntentService {
    public static final String ACTION="org.lu.hypervisor.android.receivers.ResponseBroadcastReceiver";
    public BackgroundService() {
        super("backgroundService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("backgroundService","Service running");

        Intent toastIntent= new Intent(ACTION);
        toastIntent.putExtra("resultCode", Activity.RESULT_OK);
        toastIntent.putExtra("toastMessage","I'M running after ever 15 minutes");
        sendBroadcast(toastIntent);
    }
}
