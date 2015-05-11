package com.digitaleo.sampledigitaleosdk.messagereceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.digitaleo.sampledigitaleosdk.R;
import com.digitaleo.sampledigitaleosdk.home.HomeActivity;
import com.digitaleo.sdk.push.model.messages.EOMessage;
import com.digitaleo.sdk.push.receiver.EOPushMessageIntentService;

/**
 * Created by flemontreer on 29/04/15.
 */
public class PushIntentService extends EOPushMessageIntentService {

    public PushIntentService() {
        super("PushIntentService");
    }

    @Override
    protected void handleMessage(final EOMessage message) {

        NotificationManager notificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent activityToLaunch = PendingIntent.getActivity(this, 0,
                new Intent(this, HomeActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setContentIntent(activityToLaunch)
                        .setSmallIcon(R.drawable.ic_digitaleo)
                        .setContentTitle("Sample Digitaleo SDK")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message.getText()))
                        .setContentText(message.getText())
                        .setAutoCancel(true)
                        .setNumber(12);

        notificationManager.notify(1, notificationBuilder.build());

    }
}
