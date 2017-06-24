package com.bostonkoseninsandwiches.weatherapp;

import com.bostonkoseninsandwiches.weatherapp.Helpers.WeatherData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Konstantin2 on 24.06.2017.
 */

public interface WeatherAPI {

    @GET("forecast")
    Call<List<WeatherData>> getWeatherToday(@Query("q") String name,
                                           @Query("lang") String lang,
                                           @Query("apiKey") String apiKey);

}
