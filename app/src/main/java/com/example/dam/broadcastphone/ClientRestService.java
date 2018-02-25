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
        String phone = intent.getStringExtra("phoneNumber");
        String fecha = intent.getStringExtra("date");

        Log.v("xyzyx", "State: " + state);

        Log.v("xyzyx", "State: " + state);
        Log.v("xyzyx", "Phone: " + phone);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonserver-pacogarmo.c9users.io:3000/broadcast/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRest service = retrofit.create(ApiServiceRest.class);

        CallRegister call = new CallRegister(0, state, phone, fecha);

//        getMethod(service);
        postMethod(service, call);

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
                              " - State: " + regis.getType() +
                              " - Date: " + regis.getDate() + "\n";
                }
                Log.v("xyzyx", result);
            }

            @Override
            public void onFailure(Call<ArrayList<CallRegister>> call, Throwable t) {

            }
        });
    }

    private void postMethod(ApiServiceRest service, CallRegister call) {

        Call<CallRegister> registroCall = service.postCallRegister(call);

        registroCall.enqueue(new Callback<CallRegister>() {
            @Override
            public void onResponse(Call<CallRegister> call, Response<CallRegister> response) {
                Log.d("Prueba", "Exito");
            }

            @Override
            public void onFailure(Call<CallRegister> call, Throwable t) {
                Log.d("Prueba", "Error: " + t.getMessage());
            }
        });
    }
}
