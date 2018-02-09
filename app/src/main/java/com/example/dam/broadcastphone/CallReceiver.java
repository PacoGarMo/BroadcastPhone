package com.example.dam.broadcastphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Francisco on 31/01/2018.
 */


public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final Bundle b = new Bundle(intent.getExtras());

        final Intent i = new Intent(context, ClientRestService.class);

        String state = (String) b.get("state");

//        Log.v("xyzyx", state);

        tm.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String phoneNumber) {
//                Log.v("xyzyx", phoneNumber);
//                Intent intent = new Intent(context, ClientRestService.class);
//
//                i.putExtra("state", (String) b.get("state"));
                i.putExtra("phoneNumber", phoneNumber);
                context.startService(i);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

//        i.putExtra("phoneNumber", phoneNumber);

        if(state.equals("RIGNING")){
            state = "GET";
            i.putExtra("state", state);
            context.startService(i);

        } else if(state.equals("OFFHOOK")){
            state = "CALL";
            i.putExtra("state", state);
            context.startService(i);
        }


    }
}
