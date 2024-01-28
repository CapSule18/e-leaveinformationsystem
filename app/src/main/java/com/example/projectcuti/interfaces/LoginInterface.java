package com.example.projectcuti.interfaces;

import com.example.projectcuti.login.data.model.BearerToken;
import com.example.projectcuti.login.data.model.LoggedInUser;
import com.example.projectcuti.model.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginInterface extends ApiInterface {
    @POST("login")
    Call<BearerToken> loginUser(@Body UserCredentials userCredentials);

    @GET("profile")
    Call<LoggedInUser> profile(@Header("Authorization") String authorization);
}
