package com.example.dam.broadcastphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Francisco on 31/01/2018.
 */


public class CallReceiver extends BroadcastReceiver {

    private int lastState;
    private Date startCall;
    private boolean esEntrante;
    private String savedNumber;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        }
        else{
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String numero = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            int tipoNumero = 0;
            if(stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                tipoNumero = TelephonyManager.CALL_STATE_IDLE;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                tipoNumero = TelephonyManager.CALL_STATE_OFFHOOK;
            }
            else if(stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                tipoNumero = TelephonyManager.CALL_STATE_RINGING;
            }

            onCallStateChanged(context, tipoNumero, numero);
        }
    }

    private void logCall(Context context, String numberPhone, String state, String date){
        Intent intent = new Intent(context, ClientRestService.class);
        intent.putExtra("state", state);
        intent.putExtra("phoneNumber", numberPhone);
        intent.putExtra("date", date);
        context.startService(intent);
    }

    public void onCallStateChanged(Context context, int estado, String numero) {
        switch (estado) {
            case TelephonyManager.CALL_STATE_RINGING:
                esEntrante = true;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd k:mm", Locale.getDefault());
                startCall = new Date();
                String fecha = dateFormat.format(startCall);
                savedNumber = numero;
                String tipo = "Sonando";
                logCall(context, savedNumber, tipo, fecha);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if(lastState != TelephonyManager.CALL_STATE_RINGING){
                    esEntrante = false;
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd k:mm", Locale.getDefault());
                    startCall = new Date();
                    fecha = dateFormat.format(startCall);
                    savedNumber = numero;
                    tipo = "Responder";
                    logCall(context, savedNumber, tipo, fecha);
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                dateFormat = new SimpleDateFormat("yyyy/MM/dd k:mm", Locale.getDefault());
                startCall = new Date();
                fecha = dateFormat.format(startCall);
                savedNumber = numero;
                tipo = "Colgar";
                logCall(context, savedNumber, tipo, fecha);
                break;
        }
        lastState = estado;
    }
}
