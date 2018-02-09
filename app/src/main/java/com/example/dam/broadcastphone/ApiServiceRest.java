package com.example.dam.broadcastphone;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Francisco on 31/01/2018.
 */

public interface ApiServiceRest {

    @GET("callregister")
    Call<ArrayList<CallRegister>> getCallsRegister();

    @POST("callregister")
    Call<CallRegister> postCallRegister(@Body CallRegister call);
}
