package com.example.projectcuti.support;

import com.example.projectcuti.interfaces.ApiInterface;
import com.example.projectcuti.interfaces.LoginInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://e-cuti.cikipaw.cloud/api/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiInterface getApiInterface() {

        return retrofit.create(ApiInterface.class);
    }

    public static LoginInterface getLoginInterface() {

        return retrofit.create(LoginInterface.class);
    }
}
