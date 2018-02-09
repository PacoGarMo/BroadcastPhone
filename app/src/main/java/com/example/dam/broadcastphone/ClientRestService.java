package com.example.dam.broadcastphone;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Francisco on 31/01/2018.
 */

public class ClientRestService extends Service {

    public void ClientRestService(){

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String state = intent.getStringExtra("state");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        Log.v("xyzyx", "State: " + state);

        Log.v("xyzyx", "State: " + state);
        Log.v("xyzyx", "Phone: " + phoneNumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonserver-pacogarmo.c9users.io:3000/broadcast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRest service = retrofit.create(ApiServiceRest.class);

//        getMethod(service);
//        postMethod(service, state, phoneNumber);

        return START_REDELIVER_INTENT;
    }

    private void getMethod(ApiServiceRest service) {
        Call<ArrayList<CallRegister>> regisCall = service.getCallsRegister();

        regisCall.enqueue(new Callback<ArrayList<CallRegister>>() {
            @Override
            public void onResponse(Call<ArrayList<CallRegister>> call, Response<ArrayList<CallRegister>> response) {
                ArrayList<CallRegister> array = response.body();

                String result = "";

                for (CallRegister regis : array) {
                    result += "- Id: " + regis.getId() +
                              " - State: " + regis.getState() +
                              " - Date: " + regis.getDate() +
                              " - Hour: " + regis.getHour() + "\n";
                }
                Log.v("xyzyx", result);
            }

            @Override
            public void onFailure(Call<ArrayList<CallRegister>> call, Throwable t) {

            }
        });
    }

    private void postMethod(ApiServiceRest service, String state, String phoneNumber) {

        Calendar c = new GregorianCalendar();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH + 1);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);


        CallRegister nuevo = new CallRegister( day + "/" + month + "/" + year, h + ":" + m + ":" + s, state, phoneNumber);

        Call<CallRegister> registroCall = service.postCallRegister(nuevo);

        registroCall.enqueue(new Callback<CallRegister>() {
            @Override
            public void onResponse(Call<CallRegister> call, Response<CallRegister> response) {

            }

            @Override
            public void onFailure(Call<CallRegister> call, Throwable t) {

            }
        });
    }
}
