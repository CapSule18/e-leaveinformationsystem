package com.example.projectcuti.izin.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.projectcuti.interfaces.IzinInterface;
import com.example.projectcuti.izin.data.model.Izin;
import com.example.projectcuti.support.Result;
import com.example.projectcuti.login.data.model.BearerToken;
import com.example.projectcuti.login.data.model.LoggedInUser;
import com.example.projectcuti.support.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Response;

public class IzinRepository {
    IzinInterface izinService = RetrofitClient.getIzinInterface();

    public Result<Izin> izin(String keterangan, LocalDate since, LocalDate until, String type, Context context) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());

        String sinceDate = since.format(outputFormatter);
        String untilDate = until.format(outputFormatter);

        try {
            Call<Izin> izin = izinService.izin(getToken(context), new Izin(
                    getNip(context),
                    sinceDate,
                    untilDate,
                    type,
                    keterangan
            ));

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            // Get token
            Future<Izin> izinAsync = executorService.submit(() -> {
                Response<Izin> izinResponse = izin.execute();
                return izinResponse.body();
            });

            Izin submitted = izinAsync.get();

            return new Result.Success<Izin>(submitted);
        } catch (Exception e) {
            Log.i("IZIN", e.toString());
            return new Result.Error(new IOException(e.getMessage(), e));
        }
    }

    public Result<List<Izin>> getIzin(Context context) {
        try {
            Call<List<Izin>> call = izinService.getIzin(getToken(context));

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            Future<List<Izin>> izinAsync = executorService.submit(() -> {
                Response<List<Izin>> izinResponse = call.execute();
                return izinResponse.body();
            });

            List<Izin> submitted = izinAsync.get();

            return new Result.Success<List<Izin>>(submitted);
        } catch (Exception e) {
            return new Result.Error(new IOException(e.getMessage(), e));
        }
    }

    private String getToken(Context context) {
        SharedPreferences tokenSharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
        String jsonToken = tokenSharedPreferences.getString("token", "");

        Gson gson = new Gson();

        BearerToken token = gson.fromJson(jsonToken, BearerToken.class);

        return token.getAuthorizationHeader();
    }

    private String getNip(Context context) {
        SharedPreferences userSharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        String json = userSharedPreferences.getString("user", "");

        Gson gson = new Gson();

        LoggedInUser user = gson.fromJson(json, LoggedInUser.class);

        return user.getNip();
    }
}
