package org.lu.hypervisor.android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.lu.hypervisor.android.services.BackgroundService;

public class ToastBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent= new Intent(context, BackgroundService.class);
        context.startService(serviceIntent);
    }
}
