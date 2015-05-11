package com.digitaleo.sampledigitaleosdk;

import android.app.Application;

import com.digitaleo.sdk.push.service.EOSDKPush;

public class SampleSDKApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EOSDKPush.init(getApplicationContext(), getString(R.string.eo_sdk_client_id), getString(R.string.eo_sdk_client_secret), getString(R.string.gcm_key));
    }
}
