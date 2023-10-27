package com.facebook.broadcastreciever_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
    public class MyBroadcastReciever extends BroadcastReceiver {

        private static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action != null && action.equals(SMS_RECEIVED_ACTION)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    if (pdus != null) {
                        for (Object pdu : pdus) {
                            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                            String sender = smsMessage.getDisplayOriginatingAddress();
                            String message = smsMessage.getDisplayMessageBody();

                            // Display the SMS toast message
                            Toast.makeText(context, "SMS from: " + sender + "\nMessage: " + message, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            } else if (action != null && action.equals(Intent.ACTION_BATTERY_LOW)) {
                // Display the battery low toast message
                Toast.makeText(context, "Your battery is low", Toast.LENGTH_SHORT).show();
            }
        }
    }
