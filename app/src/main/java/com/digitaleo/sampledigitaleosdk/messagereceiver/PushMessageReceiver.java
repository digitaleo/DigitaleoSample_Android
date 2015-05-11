package com.digitaleo.sampledigitaleosdk.messagereceiver;

import com.digitaleo.sdk.push.receiver.EOMessageReceiver;
import com.digitaleo.sdk.push.receiver.EOPushMessageIntentService;

/**
 * Created by flemontreer on 29/04/15.
 */
public class PushMessageReceiver extends EOMessageReceiver {

    @Override
    @SuppressWarnings("unchecked")
    protected <INTENTSERVICE extends EOPushMessageIntentService> Class<INTENTSERVICE> getPushMessageIntentService() {
        return (Class<INTENTSERVICE>) PushIntentService.class;
    }
}
