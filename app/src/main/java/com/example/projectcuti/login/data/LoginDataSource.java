package com.example.projectcuti.login.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.projectcuti.interfaces.LoginInterface;
import com.example.projectcuti.login.data.model.BearerToken;
import com.example.projectcuti.login.data.model.LoggedInUser;
import com.example.projectcuti.model.UserCredentials;
import com.example.projectcuti.support.Result;
import com.example.projectcuti.support.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    LoginInterface loginService = RetrofitClient.getLoginInterface();

    public Result<LoggedInUser> login(String username, String password, final Context context) {
        try {
            Call<BearerToken> login = loginService.loginUser(new UserCredentials(username, password));

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            // Get token
            Future<BearerToken> loginAsync = executorService.submit(() -> {
                Response<BearerToken> loginResponse = login.execute();
                return loginResponse.body();
            });

            BearerToken token = loginAsync.get();

            // store token
            this.storeToken(token, context);

            Call<LoggedInUser> profile = loginService.profile(token.getAuthorizationHeader());

            // Get user profile
            Future<LoggedInUser> profileAsync = executorService.submit(() -> {
                Response<LoggedInUser> profileResponse = profile.execute();
                return profileResponse.body();
            });

            LoggedInUser user = profileAsync.get();

            return new Result.Success<>(user);
        } catch (Exception e) {
            Log.i("LOGIN", e.toString());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    private void storeToken(BearerToken token, Context context) {
        SharedPreferences tokenSharedPreferences = context.getSharedPreferences("token", MODE_PRIVATE);
        SharedPreferences.Editor tokenEditor = tokenSharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(token);

        tokenEditor.putString("token", json);
        tokenEditor.apply();
    }
}