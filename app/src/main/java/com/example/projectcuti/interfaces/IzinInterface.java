package com.example.projectcuti.interfaces;

import com.example.projectcuti.izin.data.model.Izin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IzinInterface extends ApiInterface {

    @POST("izin")
    Call<Izin> izin(@Header("Authorization") String authorization, @Body Izin izin);


    @GET("izin")
    Call<List<Izin>> getIzin(@Header("Authorization") String authorization);
}
